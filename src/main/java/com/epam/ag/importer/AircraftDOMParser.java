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

    private List<Plane> aircrafts;
    private Plane plane;

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

    @Override
    public List parse(InputStream is, Class sourceClass) {
        return null;
    }

    private List parseDOM(Document document) {
        Element root = document.getDocumentElement();
        document.getDocumentElement().normalize();

        NodeList nList = document.getElementsByTagName("plane");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            plane = new Plane();

            // http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
//            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//                Element eElement = (Element) nNode;
//                plane.setModel(eElement.getElementsByTagName("model").item(0).getTextContent());
//                plane.setOrigin(eElement.getElementsByTagName("origin").item(0).getTextContent());
//                plane.setType(eElement.getElementsByTagName("type").item(0).getTextContent());
//                plane.setSeats(Integer.parseInt(eElement.getElementsByTagName("seats").item(0).getTextContent()));
//                plane.setWeapons(Boolean.parseBoolean(eElement.getElementsByTagName("weapons").item(0).getTextContent()));
//                plane.setMissiles(Integer.parseInt(eElement.getElementsByTagName("missiles").item(0).getTextContent()));
//                plane.setHasRadar(Boolean.parseBoolean(eElement.getElementsByTagName("hasRadar").item(0).getTextContent()));
//
//                plane.setLength(Double.parseDouble(eElement.getElementsByTagName("length").item(0).getTextContent()));
//                plane.setWidth(Double.parseDouble(eElement.getElementsByTagName("width").item(0).getTextContent()));
//                plane.setHeight(Double.parseDouble(eElement.getElementsByTagName("height").item(0).getTextContent()));
//
//                plane.setAmount(Double.parseDouble(eElement.getElementsByTagName("amount").item(0).getTextContent()));
//                plane.setCurrency(eElement.getElementsByTagName("currency").item(0).getTextContent());
//
//                aircrafts.add(plane);
//            }
        }

        return aircrafts;
    }
}
