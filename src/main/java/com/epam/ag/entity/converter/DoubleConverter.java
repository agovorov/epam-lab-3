package com.epam.ag.entity.converter;

/**
 * @author Govorov Andrey
 */
public class DoubleConverter implements Converter{
    @Override
    public Object convert(String value) {
        return Double.valueOf(value);
    }
}
