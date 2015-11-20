package com.epam.ag.entity.adapter;

import com.epam.ag.entity.Prop;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Govorov Andrey
 */
public class AdaptedProperties extends JAXBElement<String> {
    // Имя переменной и есть название элемента в xml, как-то надо подставить свои...
    public List<Prop> property = new ArrayList<>();





    private static final long serialVersionUID = 1L;
    public static final QName NAME = new QName("characteristic");

    public  AdaptedProperties() {
        super(NAME, String.class, "TEST22");
    }

    public AdaptedProperties(String value) {
        super(NAME, String.class, value);
    }

    public AdaptedProperties(String characteristic, String value) {
        super(NAME, String.class, value);
        this.characteristic = characteristic;
    }

    @Override
    public QName getName() {
        final String characteristic = getCharacteristic();
        System.out.println("AAA: " + characteristic);
        if (characteristic != null) {
            return new QName(characteristic);
        }
        return super.getName();
    }

    private String characteristic;

    @XmlTransient
    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

}
