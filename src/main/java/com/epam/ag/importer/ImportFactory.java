package com.epam.ag.importer;

import org.slf4j.Logger;

import java.io.InputStream;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ImportFactory {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ImportFactory.class);

    public static final List importFromXML(InputStream is, Class clazz) {
        Importer importer;

        // Loading specified parser class
        try {
            Class c = Class.forName(clazz.getName());
            importer = (Importer) c.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            log.error("Wrong parser name {}.", clazz.getSimpleName());

            //
            // TODO  Тут надо exit корректный какой-то...
            //
            throw new RuntimeException("Unable to create parser instance.");
        }
        return importer.parse(is);
    }
}
