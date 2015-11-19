package com.epam.ag.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Deprecated
public class Price {
    private double amount;
    private String currency;

    public Properties properties;

    public Price() {
        properties = new Properties();
    }

    public void set(String param, Object value) {
        properties.set(param, value);
    }

    public <T> T get(String param, Class<T> clazz) {
        return properties.get(param, clazz);
    }

    /*
    public Price(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }*/
}
