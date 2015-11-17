package com.epam.ag.exporter;

import java.util.List;

/**
 * @author Govorov Andrey
 */
public interface EntityExporter {

    boolean export(Object exportClass, String exportToFilename);
}
