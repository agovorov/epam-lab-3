package com.epam.ag.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Prop {
    @XmlAttribute
    public String name;

    @XmlValue
    public String value;
}
