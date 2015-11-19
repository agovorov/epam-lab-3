package com.epam.ag.entity.utils;

import com.epam.ag.entity.Property;
import com.epam.ag.entity.converter.BooleanConverter;
import com.epam.ag.entity.converter.DoubleConverter;
import com.epam.ag.entity.converter.IntegerConverter;
import com.epam.ag.entity.converter.StringConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Converter c = new Converter();
 * c.convert("Strrr", Integer.class)
 */
public class Converter {
//    public Converter(String value2, Class<String> stringClass) {
//    }
//
//
//    private static Map<String, com.epam.ag.entity.converter.Converter> converters;
//
//    public Converter() {
//        converters = new HashMap<>();
//        converters.put("int", new IntegerConverter());
//        converters.put("double", new DoubleConverter());
//        converters.put("boolean", new BooleanConverter());
//        converters.put("string", new StringConverter());
//    }

    public String convertToStr(Object object) {
        return String.valueOf(object);
    }
}
