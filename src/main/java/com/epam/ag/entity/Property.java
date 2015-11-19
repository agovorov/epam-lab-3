package com.epam.ag.entity;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Govorov Andrey
 */
public class Property<T> {

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
