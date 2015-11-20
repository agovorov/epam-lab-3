package com.epam.ag.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;

@XmlAccessorType(XmlAccessType.FIELD)
public class Prop {

    @XmlAttribute
    public String name;

    //@XmlValue
    public String value;

    public Prop() {

    }

    public Prop(String string) {
        value = string;
    }



//
//    private static final long serialVersionUID = 1L;
//    public static final QName NAME = new QName("characteristic");
//
//    public Prop(String value) {
//        super(NAME, String.class, value);
//    }
//
//    public Prop(String characteristic, String value) {
//        super(NAME, String.class, value);
//        this.characteristic = characteristic;
//    }
//
//    @Override
//    public QName getName() {
//        final String characteristic = getCharacteristic();
//        if (characteristic != null) {
//            return new QName(characteristic);
//        }
//        return super.getName();
//    }
//
//    private String characteristic;
//
//    @XmlTransient
//    public String getCharacteristic() {
//        return characteristic;
//    }
//
//    public void setCharacteristic(String characteristic) {
//        this.characteristic = characteristic;
//    }
}
