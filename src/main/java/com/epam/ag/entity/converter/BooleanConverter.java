package com.epam.ag.entity.converter;

/**
 * @author Govorov Andrey
 */
public class BooleanConverter implements Converter {
    @Override
    public Object convert(String value) {
        return Boolean.valueOf(value);
    }
}
