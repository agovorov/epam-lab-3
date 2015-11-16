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

/**
 * Govorov Andrey
 */
public class SAXHandler extends DefaultHandler {

    private static final Logger log = LoggerFactory.getLogger(SAXHandler.class);
    private StringBuilder accumulator;
    private String currentTagName;
    private List<Aircraft> aircrafts;
    private Aircraft aircraft;

    private HashMap<String, Class> elementType;

    // https://docs.oracle.com/javase/tutorial/jaxp/sax/parsing.html
    // http://www.javacodegeeks.com/2012/01/xml-parsing-using-saxparser-with.html
    @Override
    public void startDocument() throws SAXException {
        log.trace("Start SAX parsing XML file.");
        accumulator = new StringBuilder();
        aircrafts = new ArrayList<>();

        elementType = new HashMap<>();

        elementType.put("model", String.class);
        elementType.put("origin", String.class);
        elementType.put("type", String.class);
        elementType.put("seats", Integer.class);
        elementType.put("weapons", Boolean.class);
        elementType.put("missiles", Integer.class);
        elementType.put("hasRadar", Boolean.class);
        elementType.put("length", Double.class);
        elementType.put("width", Double.class);
        elementType.put("height", Double.class);
        elementType.put("amount", Double.class);
        elementType.put("currency", String.class);

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTagName = qName;

        if (qName.equals("plane")) {
            // New element
            aircraft = new Plane();
            log.trace("New plane element found.");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
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
        }

        accumulator.setLength(0);
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
        //String getMethod = "get" + methodNameCase;
        Class targetClass = aircraft.getClass().getSuperclass();

        Class<?> returnType = null;
        try {
            //Method method = targetClass.getDeclaredMethod(getMethod);
            Class typeClass = elementType.get(param);

//            returnType = method.getReturnType();
            //System.out.println(returnType.newInstance().getClass());
            Method setMethodObject = targetClass.getDeclaredMethod(
                    setMethodName,
                    typeClass
                    //returnType.newInstance().getClass()
            );

            // Может беда в этом?!
            System.out.println(typeClass.getSimpleName());
            if (typeClass.getSimpleName().equals("Integer")) {
                System.out.println(param + " ===> " + value);
                setMethodObject.invoke(aircraft, Integer.valueOf(value));
            } else {
                setMethodObject.invoke(aircraft, value);
            }

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("----- ERRROR-----");
            System.out.println(e.getMessage());
            System.out.println("-----------------");
        }


        //
        // TODO ЭТО КОШМАР!!!
        //
//
//        switch (param) {
//            case "model":
//                aircraft.setModel(value);
//                break;
//
//            case "origin":
//                aircraft.setOrigin(value);
//                break;
//
//            case "type":
//                aircraft.setType(value);
//                break;
//
//            case "seats":
//                aircraft.setSeats(Integer.parseInt(value));
//                break;
//
//            case "weapons":
//                aircraft.setWeapons(Boolean.parseBoolean(value));
//                break;
//
//            case "missiles":
//                aircraft.setMissiles(Integer.parseInt(value));
//                break;
//
//            case "hasRadar":
//                aircraft.setHasRadar(Boolean.parseBoolean(value));
//                break;
//
//            case "length":
//                aircraft.setLength(Double.parseDouble(value));
//                break;
//
//            case "width":
//                aircraft.setWidth(Double.parseDouble(value));
//                break;
//
//            case "height":
//                aircraft.setHeight(Double.parseDouble(value));
//                break;
//
//            case "amount":
//                aircraft.setPriceAmount(Double.parseDouble(value));
//                break;
//
//            case "currency":
//                aircraft.setPriceCurrency(value);
//                break;
//        }
    }

    public List returnList() {
        return aircrafts;
    }
}
