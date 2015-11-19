package com.epam.ag.entity.adapter;

import com.epam.ag.entity.Properties;
import com.epam.ag.entity.Property;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Govorov Andrey
 */
//public class CharacteristicMapAdapter extends XmlAdapter<String, Property> {
public class CharacteristicMapAdapter extends XmlAdapter<CharacteristicMapAdapter.AdaptedProperties, Properties> {

    @Override
    public Properties unmarshal(AdaptedProperties v) throws Exception {
        return null;
    }

    @Override
    public AdaptedProperties marshal(Properties v) throws Exception {
        if(null == v) {
            return null;
        }
        AdaptedProperties adaptedProperties = new AdaptedProperties();
//        for(Map.Entry<String, String> entry : map.entrySet()) {
//            Prop property = new Prop();
//            property.name = String.valueOf(entry.getKey());
//            property.value = String.valueOf(entry.getValue());
//
//
//            adaptedProperties.property.add(property);
//        }



        Map map = v.getMap();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Property property = (Property) pair.getValue();
            System.out.println(pair.getKey() + " => " + property.getValue() );

            Prop p = new Prop();
            p.name = (String) pair.getKey();
            p.value = "dd";//(String) property.getValue();
//            adaptedProperties.property.add(p);

            it.remove(); // avoids a ConcurrentModificationException
        }

        Prop p = new Prop();
        p.name = "test";
        p.value = "dd";
        adaptedProperties.property.add(p);
        adaptedProperties.property.add(p);
        adaptedProperties.property.add(p);
        adaptedProperties.property.add(p);
        adaptedProperties.property.add(p);
        adaptedProperties.property.add(p);
        return adaptedProperties;
    }

    public static class AdaptedProperties {
        public List<Prop> property = new ArrayList<>();
    }

    public static class Prop {
        @XmlAttribute
        public String name;

        @XmlValue
        public String value;
    }





    /*
                class MapAdapter extends XmlAdapter<MapElements[], Map<String, Boolean>>
            {
              public MapElements[] marshal(Map<String, Boolean> arg0) throws Exception
              {
                MapElements[] mapElements = new MapElements[arg0.size()];

                int i = 0;
                for (Map.Entry<String, Boolean> entry : arg0.entrySet())
                  mapElements[i++] = new MapElements(entry.getKey(), entry.getValue());

                return mapElements;
              }

                 */
//    @Override
//    public Property[] marshal(Properties v) throws Exception {
//        Property[] propertyArray = new Property[10];
//
//        int i = 0;
//        Map map = v.getMap();
//        Iterator it = map.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry)it.next();
//
//            Property property = (Property) pair.getValue();
//            propertyArray[i] = property;
//
//            i++;
//            System.out.println(pair.getKey() + " => " + property.getValue() );
//            it.remove(); // avoids a ConcurrentModificationException
//        }
//        return propertyArray;
//    }
}
