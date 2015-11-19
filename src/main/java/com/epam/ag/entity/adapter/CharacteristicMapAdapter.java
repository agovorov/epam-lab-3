package com.epam.ag.entity.adapter;

import com.epam.ag.entity.Properties;
import com.epam.ag.entity.Property;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Govorov Andrey
 */
//public class CharacteristicMapAdapter extends XmlAdapter<String, Property> {
public class CharacteristicMapAdapter extends XmlAdapter<String[], Properties> {


    @Override
    public Properties unmarshal(String[] v) throws Exception {
        return null;
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
    @Override
    public String[] marshal(Properties v) throws Exception {
        Property[] propertyArray = new Property[10];

        int i = 0;
        Map map = v.getMap();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Property property = (Property) pair.getValue();

            propertyArray[i] = property;
            i++;
            // System.out.println(pair.getKey() + " => " + property.getValue() );
            it.remove(); // avoids a ConcurrentModificationException
        }

        // Test
        String[] s = new String[6];
        s[0] = new String("test 1");
        s[1] = new String("test 2");
        s[2] = new String("test 3");
        s[3] = new String("");
        s[4] = new String("");
        s[5] = new String("");
        return s;
    }
}
