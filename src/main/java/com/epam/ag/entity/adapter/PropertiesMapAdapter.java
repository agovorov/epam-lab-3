package com.epam.ag.entity.adapter;

import com.epam.ag.entity.Prop;
import com.epam.ag.entity.Properties;
import com.epam.ag.entity.Property;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Iterator;
import java.util.Map;

/**
 * http://stackoverflow.com/questions/5514752/xml-element-with-attribute-and-content-using-jaxb
 * http://stackoverflow.com/questions/3284786/java-jaxb-unmarshall-xml-attributes-to-specific-java-object-attributes
 *
 * !!!
 * http://stackoverflow.com/questions/26325201/how-can-i-make-a-class-field-become-a-tag-name-using-jaxb
 * @author Govorov Andrey
 */
public class PropertiesMapAdapter extends XmlAdapter<AdaptedProperties, Properties> {

    @Override
    public Properties unmarshal(AdaptedProperties v) throws Exception {
        return null;
    }

    @Override
    public AdaptedProperties marshal(Properties v) throws Exception {
        if (null == v) {
            System.out.println("NULL!");
            return null;
        }

        AdaptedProperties adaptedProperties = new AdaptedProperties();

//        Map map = v.getMap();
//        Iterator it = map.entrySet().iterator();
//        while (it.hasNext()) {
//
//            Map.Entry pair = (Map.Entry) it.next();
//            Property property = (Property) pair.getValue();
//            System.out.println(pair.getKey() + " ==> " + property.getValueAsString());
//
//            //System.out.println( property.getValue() + " => " + property.getValueAsString().getClass()  );
//
//
//            Prop p = new Prop();
//            p.name =  (String) pair.getKey();
//            p.value = property.getValueAsString();
//
//            adaptedProperties.property.add(p);
//            it.remove(); // avoids a ConcurrentModificationException
//        }

        Prop p = new Prop();
        p.name = "test";
        p.value = "da-ta-dam";

        adaptedProperties.property.add(p);
        return adaptedProperties;
    }
}
