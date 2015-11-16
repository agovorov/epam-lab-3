package com.epam.ag.entity.converter;

/**
 * @author Govorov Andrey
 */
public class IntegerConverter implements Converter {
    @Override
    public Object convert(String value) {
        return Integer.valueOf(value);
    }
}
