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
        propertyMap.put(param, new Property<>(value));
    }

    //
    // TODO Что подсвечивает Idea
    //
    public <T> T get(String param, Class<T> clazz) {
        try {
            return (T) propertyMap.get(param);
        } catch(ClassCastException e) {
            return null;
        }
        ///return (T) propertyMap.get(param);
        //return clazz.cast(propertyMap.get(param));
    }


    @Override
    public String toString() {
        return this.getClass() + " [" +
                propertyMap.toString() + "]";
    }
}
