package com.epam.ag.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Govorov Andrey
 */
public abstract class Aircraft {
    private String model;
    private String origin;


    // Characteristics
    private String type;
    private int seats;
    private boolean weapons;
    private int missiles;
    private boolean hasRadar;

    // Parameters
    private double length;
    private double width;
    private double height;

    // Price
    private double priceAmount;
    private String priceCurrency;

    private Properties characteristics;
    private Parameters parameters;
    private Price price;

    public Aircraft() {
        characteristics = new Properties(); //Characteristic();
        parameters = new Parameters();
        price = new Price();
    }

    // TEST
    public Properties getChar() {
        return characteristics;
    }

    public void setCharacteristic(String param, Object value) {
        characteristics.set(param, new Property<>(value));
    }

    public <T> T get(String name, Class<T> clazz) {
        return characteristics.get(name, clazz);
    }

    /*
    public void setCharacteristic(String param, String value) {
        Characteristic c = new Characteristic(param, value);
        //this.characteristics = charsacteristic;
    }*/

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

    @Deprecated
    public String getType() {
        return type;
    }

    @Deprecated
    public void setType(String type) {
        this.type = type;
    }

    @Deprecated
    public int getSeats() {
        return seats;
    }

    @Deprecated
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Deprecated
    public boolean isWeapons() {
        return weapons;
    }

    @Deprecated
    public void setWeapons(boolean weapons) {
        this.weapons = weapons;
    }

    @Deprecated
    public int getMissiles() {
        return missiles;
    }

    @Deprecated
    public void setMissiles(int missiles) {
        this.missiles = missiles;
    }

    @Deprecated
    public boolean isHasRadar() {
        return hasRadar;
    }

    @Deprecated
    public void setHasRadar(boolean hasRadar) {
        this.hasRadar = hasRadar;
    }

    @Deprecated
    public double getLength() {
        return length;
    }

    @Deprecated
    public void setLength(double length) {
        this.length = length;
    }

    @Deprecated
    public double getWidth() {
        return width;
    }

    @Deprecated
    public void setWidth(double width) {
        this.width = width;
    }

    @Deprecated
    public double getHeight() {
        return height;
    }

    @Deprecated
    public void setHeight(double height) {
        this.height = height;
    }

    @Deprecated
    public double getAmount() {
        return priceAmount;
    }

    @Deprecated
    public void setAmount(double priceAmount) {
        this.priceAmount = priceAmount;
    }

    @Deprecated
    public String getCurrency() {
        return priceCurrency;
    }

    @Deprecated
    public void setCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "model='" + model + '\'' +
                ", origin='" + origin + '\'' +
                ", type='" + type + '\'' +
                ", seats=" + seats +
                ", weapons=" + weapons +
                ", missiles=" + missiles +
                ", hasRadar=" + hasRadar +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", priceAmount=" + priceAmount +
                ", priceCurrency='" + priceCurrency + '\'' +
                '}';
    }
}
