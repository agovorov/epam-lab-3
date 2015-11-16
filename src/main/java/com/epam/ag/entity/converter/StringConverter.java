package com.epam.ag.entity.converter;

/**
 * @author Govorov Andrey
 */
public class StringConverter implements Converter{
    @Override
    public Object convert(String value) {
        return value;
    }
}
