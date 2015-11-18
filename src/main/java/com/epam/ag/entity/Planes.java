package com.epam.ag.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Govorov Andrey
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "planes")
public class Planes {

    @XmlElement(name = "plane", type = Plane.class)
    private List<Plane> planes = new ArrayList<>();

    public Planes() {}

    public Planes(List<Plane> planes) {
        this.planes = planes;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }
}
