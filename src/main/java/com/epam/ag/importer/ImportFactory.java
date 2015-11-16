package com.epam.ag.importer;

import com.epam.ag.entity.Plane;
import org.slf4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class ImportFactory {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ImportFactory.class);

    public static final List importFromXML(File f, Class clazz) {
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
        return importer.parse(f);
    }
}
