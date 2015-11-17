package com.epam.ag.importer;

import com.epam.ag.entity.Aircraft;
import com.epam.ag.entity.Plane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class AircraftDOMParser implements Importer {

    private List<Aircraft> aircrafts;
    private Aircraft aircraft;

    public AircraftDOMParser() {
        aircrafts = new ArrayList<>();
    }

    @Override
    public List parse(InputStream is) {
        Document document;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            document = documentBuilder.parse(is);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return parseDOM(document);
    }

    private List parseDOM(Document document) {
        Element root = document.getDocumentElement();
        document.getDocumentElement().normalize();
        System.out.println("Root element :" + document.getDocumentElement().getNodeName());

        NodeList nList = document.getElementsByTagName("plane");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            aircraft = new Plane();

            // http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                aircraft.setModel(eElement.getElementsByTagName("model").item(0).getTextContent());
                aircraft.setOrigin(eElement.getElementsByTagName("origin").item(0).getTextContent());
                aircraft.setType(eElement.getElementsByTagName("type").item(0).getTextContent());
                aircraft.setSeats(Integer.parseInt(eElement.getElementsByTagName("seats").item(0).getTextContent()));
                aircraft.setWeapons(Boolean.parseBoolean(eElement.getElementsByTagName("weapons").item(0).getTextContent()));
                aircraft.setMissiles(Integer.parseInt(eElement.getElementsByTagName("missiles").item(0).getTextContent()));
                aircraft.setHasRadar(Boolean.parseBoolean(eElement.getElementsByTagName("hasRadar").item(0).getTextContent()));

                aircraft.setLength(Double.parseDouble(eElement.getElementsByTagName("length").item(0).getTextContent()));
                aircraft.setWidth(Double.parseDouble(eElement.getElementsByTagName("width").item(0).getTextContent()));
                aircraft.setHeight(Double.parseDouble(eElement.getElementsByTagName("height").item(0).getTextContent()));

                aircraft.setAmount(Double.parseDouble(eElement.getElementsByTagName("amount").item(0).getTextContent()));
                aircraft.setCurrency(eElement.getElementsByTagName("currency").item(0).getTextContent());

                aircrafts.add(aircraft);
            }
        }

        return aircrafts;
    }
}
