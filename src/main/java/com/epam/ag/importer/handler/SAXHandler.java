package com.epam.ag.importer.handler;

import com.epam.ag.entity.Aircraft;
import com.epam.ag.entity.Plane;
import com.epam.ag.entity.converter.BooleanConverter;
import com.epam.ag.entity.converter.Converter;
import com.epam.ag.entity.converter.DoubleConverter;
import com.epam.ag.entity.converter.IntegerConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Govorov Andrey
 */
public class SAXHandler extends DefaultHandler {

    private static final Logger log = LoggerFactory.getLogger(SAXHandler.class);
    private static Map<String, Class<?>> methodTypes;
    private static Map<String, Method> methods;
    private static Map<String, Converter> converters;

    private StringBuilder accumulator;
    private String currentTagName;
    private List<Aircraft> aircrafts;
    private Aircraft aircraft;

    /**
     * Extract all methods from Aircraft class with its parameters types to methodTypes map
     */
    public void scanClassForMethods() {
        methodTypes = new HashMap<>();
        methods = new HashMap<>();

        Class targetClass = Aircraft.class;
        Method[] declaredMethods = targetClass.getDeclaredMethods();
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
    public void startDocument() throws SAXException {
        log.trace("Start SAX parsing XML file.");

        // Scanning class for methods and types
        scanClassForMethods();

        // Init converters
        fillConverters();

        accumulator = new StringBuilder();
        aircrafts = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTagName = qName;
        if (qName.equals("plane")) {
            // New element
            aircraft = new Plane();
            log.trace("New plane element found.");
        }
        accumulator.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        log.trace("Append new characters {}", ch);
        accumulator.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (currentTagName.equals(qName)) {
            // Add field to entity
            fillAircraftClass(qName, accumulator.toString().trim());
        }

        if (qName.equals("plane")) {
            // Plane is done. Add to list
            aircrafts.add(aircraft);
            log.trace("Object plane added to list");
        }
    }

    @Override
    public void endDocument() throws SAXException {
        log.trace("SAX parsing done.");
    }

    /**
     * Fill object plane with data
     *
     * @param param
     * @param value
     */
    private void fillAircraftClass(String param, String value) {
        String methodNameCase = param.substring(0, 1).toUpperCase() + param.substring(1);
        String setMethodName = "set" + methodNameCase;

        if (!methodTypes.containsKey(setMethodName)) {
            return;
        }

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

    public List returnList() {
        return aircrafts;
    }
}
