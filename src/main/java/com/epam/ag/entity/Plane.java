package com.epam.ag.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Govorov Andrey
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Plane  {
    private String model;
    private String origin;

    private Characteristics characteristics;
    private Parameters parameters;
    private Price price;

    public Plane() {
        characteristics = new Characteristics();
        parameters = new Parameters();
        price = new Price();
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

    /*public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }*/
}
