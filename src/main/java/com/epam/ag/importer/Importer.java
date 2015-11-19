package com.epam.ag.importer;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public interface Importer {

    List parse(InputStream is);
    List parse(InputStream is, Class sourceClass);
}
