package com.epam.ag.importer;

import com.epam.ag.entity.Planes;
import org.slf4j.Logger;

import java.io.InputStream;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ImportFactory {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ImportFactory.class);

    /**
     *
     * @param xmlStream
     * @param parserClass Parser class (type)
     * @param destinatinClass
     * @return
     */
    public static List ImportFromXML(InputStream xmlStream, Class<? extends Importer> parserClass, Class destinatinClass) {
        Importer importer;
        try {
            Class c = Class.forName(parserClass.getName());
            importer = (Importer) c.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            log.error("Wrong parser name {}.", parserClass.getSimpleName());
            throw new RuntimeException("Unable to create parser instance.");
        }
        return importer.parse(xmlStream, destinatinClass);
    }
}
