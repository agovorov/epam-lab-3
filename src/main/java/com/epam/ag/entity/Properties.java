package com.epam.ag.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Govorov Andrey
 */
public class Properties {

    private Map<String, Property> propertyMap;

    public Properties () {
        propertyMap = new HashMap<>();
    }

    public void set(String param, Object value) {
        propertyMap.put(param, new Property<>(value));
    }

    /*
    public Object get(String param) {
        // Получаем тип параметра
        Object o = propertyMap.get(param).getValue();
        // Class clazz =  .getValue().getClass();
        Class clazz = o.getClass();

        return clazz.cast(o);
    }
    */

    public <T> T get(String param, Class<T> clazz) {
        //return (T) propertyMap.get(param).getValue();
        Property property = propertyMap.get(param);
        Object value = property.getValue();
        return clazz.cast(value);
    }

    @Override
    public String toString() {
        return this.getClass() + " [" +
                propertyMap.toString() + "]";
    }
}
