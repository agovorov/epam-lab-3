package com.epam.ag.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/*
@XmlType(propOrder = { "name", "members" }, name = "group")
@XmlType(propOrder = { "lastName", "firstName", "birthDate" }, name = "person")

*/

//http://stackoverflow.com/questions/2895658/jaxb-xmlaccesstype-property-example#2895658
@XmlAccessorType(XmlAccessType.FIELD)
// @XmlAccessorType(XmlAccessType.PROPERTY)
//@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.PROPERTY)
/*
@XmlType(name = "basicStruct", propOrder = {
    "intValue",
    "stringArray",
    "stringValue"
)
 */
@Deprecated
public class Characteristics {

    // My "annotation"
    private String type;
    private int seats;
    private boolean weapons;
    private int missiles;
    private boolean hasRadar;

    @XmlTransient
    private Properties properties;

    public Characteristics() {
        properties = new Properties();
    }

    public void set(String param, Object value) {
        properties.set(param, value);
    }

    //@XmlAttribute
    public <T> T get(String param, Class<T> clazz) {
        return properties.get(param, clazz);
    }
}
