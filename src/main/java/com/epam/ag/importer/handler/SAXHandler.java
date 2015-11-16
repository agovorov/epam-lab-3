package com.epam.ag.importer.handler;

import com.epam.ag.entity.Aircraft;
import com.epam.ag.entity.Plane;
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
    private static HashMap<String, Class<?>> methodTypes;
    //private static Map<Class, Function> converters ?? Javascript OR Apache XPath
    private static Map<Class, Object> converters;

    private static HashMap<String, Class> elementType;

    private StringBuilder accumulator;
    private String currentTagName;
    private List<Aircraft> aircrafts;
    private Aircraft aircraft;

    /**
     * Extract all methods from Aircraft class with its parameters types to methodTypes hashmap
     */
    public void scanClassForMethods() {
        methodTypes = new HashMap<>();

        Class targetClass = Aircraft.class;
        Method[] declaredMethods = targetClass.getDeclaredMethods();
        if (declaredMethods.length > 0) {
            for (Method method : declaredMethods) {
                Class<?>[] types = method.getParameterTypes();
                if (types.length > 0) {
                    final Class<?> c = types[0];
                    methodTypes.put(method.getName(), c);
                }
            }
        }
    }

    public void fillConverters() {
        /*
        converters.put(String.class, new Command() {
            public void runCommand() { System.out.println("help"); };
        });
        */

        converters.put(String.class, new Object(){
           public String convert(String value) {
               return value;
           }
        });

        converters.put(int.class, new Object(){
            public int convert(String value) {
                return Integer.valueOf(value);
            }
        });
    }

    // https://docs.oracle.com/javase/tutorial/jaxp/sax/parsing.html
    // http://www.javacodegeeks.com/2012/01/xml-parsing-using-saxparser-with.html
    @Override
    public void startDocument() throws SAXException {
        log.trace("Start SAX parsing XML file.");

        // Scanning class for methods and types
        scanClassForMethods();

        accumulator = new StringBuilder();
        aircrafts = new ArrayList<>();

        /*
        elementType = new HashMap<>();

        elementType.put("model", String.class);
        elementType.put("origin", String.class);
        elementType.put("type", String.class);
        elementType.put("seats", int.class);
        elementType.put("weapons", boolean.class);
        elementType.put("missiles", int.class);
        elementType.put("hasRadar", boolean.class);
        elementType.put("length", double.class);
        elementType.put("width", double.class);
        elementType.put("height", double.class);
        elementType.put("amount", double.class);
        elementType.put("currency", String.class);
        */
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
     * Fill object with data
     *
     * @param param
     * @param value
     */
    private void fillAircraftClass(String param, String value) {
        String methodNameCase = param.substring(0, 1).toUpperCase() + param.substring(1);
        String setMethodName = "set" + methodNameCase;

        //methodTypes
        System.out.println(setMethodName);
        if (!methodTypes.containsKey(setMethodName)) {
            return;
        }

        Class<?> fieldType = methodTypes.get(setMethodName);
        System.out.println(fieldType);
        System.out.println("------");


        // Без этого не получается вызвать метод класса
        Class targetClass = aircraft.getClass().getSuperclass();
        try {
            Method setMethodObject = targetClass.getDeclaredMethod(
                    setMethodName,
                    fieldType
            );

            setMethodObject.invoke(aircraft, String.valueOf(value));


        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        /*try {

            if (typeClass.getSimpleName().equals("int")) {
                setMethodObject.invoke(aircraft, Integer.valueOf(value));
            } else if (typeClass.getSimpleName().equals("boolean")) {
                setMethodObject.invoke(aircraft, Boolean.valueOf(value));
            } else if (typeClass.getSimpleName().equals("double")) {
                setMethodObject.invoke(aircraft, Double.valueOf(value));
            } else {
                setMethodObject.invoke(aircraft, String.valueOf(value));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.err.println("----- ERRROR-----");
            System.err.println(e.getMessage());
            System.err.println("-----------------");
        }
        */
    }


    public List returnList() {
        return aircrafts;
    }
}
