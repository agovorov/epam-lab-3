package com.epam.ag.entity;

import com.epam.ag.entity.adapter.PropertiesMapAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

// http://stackoverflow.com/questions/3941479/jaxb-how-to-marshall-map-into-keyvalue-key

/**
 * @author Govorov Andrey
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Plane {
    private String model;
    private String origin;

    //@XmlJavaTypeAdapter(value=PropertiesMapAdapter.class,  type=Property.class)
    @XmlJavaTypeAdapter(value=PropertiesMapAdapter.class)
    private Properties characteristics;

    @XmlTransient
    private Properties parameters;

    @XmlTransient
    private Properties price;

    public Plane() {
        characteristics = new Properties();
        parameters = new Properties();
        price = new Properties();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public <T> T getCharacteristic(String param, Class clazz) {
        return (T) characteristics.get(param, clazz);
    }

    public void setCharacteristic(String param, Object value) {
        characteristics.set(param, value);
    }

    public <T> T getParameter(String param, Class clazz) {
        return (T) parameters.get(param, clazz);
    }

    public void setParameter(String param, Object value) {
        parameters.set(param, value);
    }


    public <T> T getPrice(String param, Class clazz) {
        return (T) price.get(param, clazz);
    }

    public void setPrice(String param, Object value) {
        price.set(param, value);
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "model='" + model + '\'' +
                ", origin='" + origin + '\'' +
                ", characteristics='" + characteristics + '\'' +
                ", parameters=" + parameters +
                ", price=" + price + '\'' +
                '}';
    }
}
