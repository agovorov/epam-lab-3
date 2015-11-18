package com.epam.ag.entity;

/**
 * @author Govorov Andrey
 */
public class Property<T> {
    private T value;

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
