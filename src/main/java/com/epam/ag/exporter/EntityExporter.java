package com.epam.ag.exporter;

/**
 * @author Govorov Andrey
 */
public interface EntityExporter {

    boolean export(Object exportClass, String exportToFilename);
}
