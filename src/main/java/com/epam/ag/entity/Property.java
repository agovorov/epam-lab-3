package com.epam.ag.entity;

import com.epam.ag.entity.utils.Converter;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author Govorov Andrey
 */
public class Property<T> {
    // Это лишнее
    @XmlAttribute
    public String name;
    public String value2;


    //@XmlValue
    private T value;

    public Property() {
        // For jaxb
    }

    public Property(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public String getValueAsString() {
        Converter converter = new Converter();
        return converter.convertToStr(value);
    }

    @Override
    public String toString() {
        return "Property{value=" + value + "}";
    }
}
