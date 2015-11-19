package com.epam.ag.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlTransient;

//@XmlAccessorType(XmlAccessType.FIELD)
@Deprecated
public class Parameters {

    // My "annotation"
    private double length;
    private double width;
    private double height;

    public Properties properties;

    public Parameters() {
        properties = new Properties();
    }

    public void set(String param, Object value) {
        properties.set(param, value);
    }

    public <T> T get(String param, Class<T> clazz) {
        return properties.get(param, clazz);
    }
}
