package com.epam.ag.entity;

/*
private List<Action> defaultActions = Arrays.asList(
            new DeleteEmptyLinesAction(),
            new TrimEachLineAction(),
            new ReplaceAction("\t", ""),
            new ReplaceAction("\\s{2,}", "")
    );
 */

/**
 * Characteristics class
 *
 * @example: Aircraft plane = new Plane();
 * @deprecated
 */

public class Characteristic {

    private Properties properties;

    public Characteristic() {
        properties = new Properties();
    }

    public void setChar(String param, Object value) {
        properties.set(param, new Property<>(value));
    }

    public <T> T get(String param, Class<T> clazz) {

        return properties.get(param, clazz);
    }
}
