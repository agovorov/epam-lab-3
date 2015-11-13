package com.epam.ag.importer.handler;

import com.epam.ag.entity.Aircraft;
import com.epam.ag.entity.Plane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Govorov Andrey
 */
public class SAXHandler extends DefaultHandler {

    private static final Logger log = LoggerFactory.getLogger(SAXHandler.class);
    private StringBuilder accumulator;
    private String currentTagName;

    private Aircraft aircraft = new Plane();

    @Override
    public void startDocument() throws SAXException {
        log.trace("Start SAX parsing XML file.");
        accumulator = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTagName = qName;
        //log.info("startElement: {}", qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        accumulator.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (currentTagName.equals(qName)) {
            log.info(qName + " = `" + accumulator.toString().trim() + "`");

            // Add field to entity
            fillAircraftClass(qName, accumulator.toString().trim());
        }
        accumulator.setLength(0);
    }

    @Override
    public void endDocument() throws SAXException {
        log.trace("SAX parsing done.");
        log.info(aircraft.toString());
    }

    /**
     * Fill object with data
     *
     * @param param
     * @param value
     */
    private void fillAircraftClass(String param, String value) {
        //
        // TODO ЭТО КОШМАР!!!
        //
        switch (param) {
            case "model":
                aircraft.setModel(value);
                break;

            case "origin":
                aircraft.setOrigin(value);
                break;

            case "type":
                aircraft.setType(value);
                break;

            case "seats":
                aircraft.setSeats(Integer.parseInt(value));
                break;

            case "weapons":
                aircraft.setWeapons(Boolean.parseBoolean(value));
                break;

            case "missiles":
                aircraft.setMissiles(Integer.parseInt(value));
                break;

            case "hasRadar":
                aircraft.setHasRadar(Boolean.parseBoolean(value));
                break;

            case "length":
                aircraft.setLength(Double.parseDouble(value));
                break;

            case "width":
                aircraft.setWidth(Double.parseDouble(value));
                break;

            case "height":
                aircraft.setHeight(Double.parseDouble(value));
                break;

            case "amount":
                aircraft.setPriceAmount(Double.parseDouble(value));
                break;

            case "currency":
                aircraft.setPriceCurrency(value);
                break;
        }
    }
}
