package com.epam.ag.entity.adapter;

import com.epam.ag.entity.Prop;

import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;

/**
 * @author Govorov Andrey
 */
@Deprecated
@XmlRegistry
public class ObjectFactory {

    @XmlElementDecl(name = "characteristic")
    public Prop createCharacteristic(String value) {
        return new Prop(value);
    }

}
