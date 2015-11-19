package com.epam.ag.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author Govorov Andrey
 */
public class Property<T> {
    // Это лишнее
    @XmlAttribute
    public String name;

    @XmlValue
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

    @Override
    public String toString() {
        return "Property{value=" + value + "}";
    }
}
