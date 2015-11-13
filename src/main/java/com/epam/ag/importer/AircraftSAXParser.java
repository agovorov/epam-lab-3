package com.epam.ag.importer;

import com.epam.ag.importer.handler.SAXHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * @author Govorov Andrey
 */
public class AircraftSAXParser implements Importer {

    private static final Logger log = LoggerFactory.getLogger(AircraftSAXParser.class);

    @Override
    public List parse(File f) {
        // Read XML
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
            parser.parse(f, new SAXHandler());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
