package com.epam.ag.exporter;

import org.slf4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class JaxBExporter implements EntityExporter {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(JaxBExporter.class);

    @Override
    public boolean export(Object exportClass, String exportToFilename) {
        File file = new File(exportToFilename);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(exportClass.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

           // jaxbMarshaller.marshal(exportClass, file);
            jaxbMarshaller.marshal(exportClass, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
            log.error("Unable to create XML file from object. ({})", e.getMessage());
            return false;
        }
        return true;
    }
}
