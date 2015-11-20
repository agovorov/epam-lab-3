package com.epam.ag.entity.adapter;

import com.epam.ag.entity.Prop;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class AdaptedProperties {
    // Имя переменной и есть название элемента в xml, как-то надо подставить свои...
    public List<Prop> property = new ArrayList<>();


    /*
    @XmlAttribute String name;
    @XmlElement List<Value> value = new ArrayList<Value>();
    Map<String, String> map = new HashMap<String, String>();

    //
    private static class Value {
        @XmlAttribute String name;
        @XmlValue
        String value;
    }

    public void beforeMarshal(Marshaller marshaller) {
        for(Map.Entry<String, String> entry : map.entrySet()) {
            Value aValue = new Value();
            aValue.name = entry.getKey();
            aValue.value = entry.getValue();
            value.add(aValue);
        }
    }

    public void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
        for(Value aValue : value) {
            map.put(aValue.name, aValue.value);
        }
    }
    */
}
