package com.epam.ag.entity.adapter;

import com.epam.ag.entity.Property;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class CharacteristicBoundType {

    public List<Property> property = new ArrayList<Property>();

    /*
    @XmlAttribute
    private Object key;

    @XmlValue
    private Object value;

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getKey() {
        return key;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
    */
}
