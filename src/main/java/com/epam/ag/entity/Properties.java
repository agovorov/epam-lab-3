package com.epam.ag.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Govorov Andrey
 */
public class Properties {

    private Map<String, Property> propertyMap;

    public Properties () {
        propertyMap = new HashMap<>();
    }

    public void set(String param, Property value) {
        propertyMap.put(param, new Property(value));
    }

    public <T> T get(String param, Class<T> clazz) {
        System.out.println( clazz );
        return (T) propertyMap.get(param);
    }

    @Override
    public String toString() {
        return this.getClass() + " [" +
                propertyMap.toString() + "]";
    }
}
