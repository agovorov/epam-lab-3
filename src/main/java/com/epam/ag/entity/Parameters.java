package com.epam.ag.entity;

public class Parameters {

    // My "annotation"
    private double length;
    private double width;
    private double height;

    public Properties properties;

    public Parameters() {
        properties = new Properties();
    }

    public void set(String param, Object value) {
        properties.set(param, value);
    }

    public <T> T get(String param, Class<T> clazz) {
        return properties.get(param, clazz);
    }


}
