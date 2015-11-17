package com.epam.ag.importer;

import com.epam.ag.entity.Aircraft;
import com.epam.ag.entity.Plane;
import com.epam.ag.entity.converter.BooleanConverter;
import com.epam.ag.entity.converter.Converter;
import com.epam.ag.entity.converter.DoubleConverter;
import com.epam.ag.entity.converter.IntegerConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Govorov Andrey
 */
public class AircraftSTAXParser implements Importer {
    // Тут дублирование с SaxParser...
    private static final Logger log = LoggerFactory.getLogger(AircraftSTAXParser.class);
    private static Map<String, Class<?>> methodTypes;
    private static Map<String, Method> methods;
    private static Map<String, Converter> converters;

    // new data
    private static Class targetClass;
    private static String targetClassTagName;
    private Deque stack;
    // ----------------

    private StringBuilder accumulator;
    private String currentTagName;
    private List<Aircraft> aircrafts;
    private Aircraft aircraft;

    // http://tutorials.jenkov.com/java-xml/sax-vs-stax.html

    public AircraftSTAXParser() {
        // пока вшиваю, потом можно передавать параметром
        targetClass = Plane.class;
        targetClassTagName = targetClass.getSimpleName().toLowerCase();
        methodTypes = new HashMap<>();
        methods = new HashMap<>();
        stack = new ArrayDeque();
        accumulator = new StringBuilder();
        aircrafts = new ArrayList<>();

        scanClassForMethods(Aircraft.class);
        fillConverters();
        log.trace("Main model element: {}", targetClassTagName);
    }

    public void scanClassForMethods(Class clazz) {
        methodTypes = new HashMap<>();
        methods = new HashMap<>();

        Method[] declaredMethods = clazz.getMethods();
        if (declaredMethods.length > 0) {
            for (Method method : declaredMethods) {
                Class<?>[] types = method.getParameterTypes();
                if (types.length > 0) {
                    final Class<?> c = types[0];
                    methodTypes.put(method.getName(), c);
                    methods.put(method.getName(), method);
                }
            }
        }
    }

    /**
     * Map of converters
     */
    public void fillConverters() {
        converters = new HashMap<>();
        converters.put("int", new IntegerConverter());
        converters.put("double", new DoubleConverter());
        converters.put("boolean", new BooleanConverter());
    }
    @Override
    public List parse(InputStream is) {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        try {
            XMLStreamReader reader = factory.createXMLStreamReader(is);
            while (reader.hasNext()) {
                int next = reader.next();
                switch (next) {
                    case XMLStreamConstants.START_ELEMENT:
                        stack.addLast(reader.getLocalName());
                        accumulator.setLength(0);
                        if (reader.getLocalName().equals(targetClassTagName)) {
                            aircraft = (Aircraft) targetClass.newInstance();
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        addDataToModel(reader.getLocalName(), accumulator.toString());
                        if (reader.getLocalName().equals(targetClassTagName)) {
                            aircrafts.add(aircraft);
                        }
                        stack.removeLast();
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        accumulator.append(reader.getText());
                        break;

                    case XMLStreamConstants.END_DOCUMENT:


                        break;
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return aircrafts;
    }

    private void addDataToModel(String param, String value) {
        /// String previousTagName = getPreviousStackValue();
        String methodNameCase = param.substring(0, 1).toUpperCase() + param.substring(1);
        String setMethodName = "set" + methodNameCase;

        if (!methodTypes.containsKey(setMethodName)) return;

        Class<?> fieldType = methodTypes.get(setMethodName);
        String typeClass = fieldType.getSimpleName();
        Method setMethodObject = methods.get(setMethodName);
        try {
            if (converters.containsKey(typeClass)) {
                Converter converter = converters.get(typeClass);
                setMethodObject.invoke(aircraft, converter.convert(value));
            } else {
                // Default String value
                setMethodObject.invoke(aircraft, value);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("Wrong model param `{}`" + param);
        }
    }
}
