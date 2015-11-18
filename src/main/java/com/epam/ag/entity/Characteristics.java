package com.epam.ag.entity;

public class Characteristics {

    // My "annotation"
    private String type;
    private int seats;
    private boolean weapons;
    private int missiles;
    private boolean hasRadar;

    private Properties properties;

    public Characteristics() {
        properties = new Properties();
    }

    public void set(String param, Object value) {
        properties.set(param, value);
    }

    public <T> T get(String param, Class<T> clazz) {
        return properties.get(param, clazz);
    }
}
