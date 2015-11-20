package com.epam.ag.entity;

import com.epam.ag.entity.adapter.PropertiesAdapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Govorov Andrey
 */
@XmlRootElement(name = "Properties")
public class Properties {

    // @XmlJavaTypeAdapter(PropertiesAdapter.class)
    private Map<String, Property> propertyMap;

    public Properties () {
        propertyMap = new HashMap<>();
    }

    public void set(String param, Object value) {
        propertyMap.put(param, new Property<>(value));
    }

    public <T> T get(String param, Class<T> clazz) {
        //return (T) propertyMap.get(param).getValue();
        Property property = propertyMap.get(param);
        Object value = property.getValue();
        return clazz.cast(value);
    }

    public Map getMap() {
        return propertyMap;
    }

    @Override
    public String toString() {
        return this.getClass() + " [" +
                propertyMap.toString() + "]";
    }
}
