package com.epam.ag.entity;

public class Characteristic {
    private String type;
    private int seats;
    private boolean weapons;
    private int missiles;
    private boolean hasRadar;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isWeapons() {
        return weapons;
    }

    public void setWeapons(boolean weapons) {
        this.weapons = weapons;
    }

    public int getMissiles() {
        return missiles;
    }

    public void setMissiles(int missiles) {
        this.missiles = missiles;
    }

    public boolean isHasRadar() {
        return hasRadar;
    }

    public void setHasRadar(boolean hasRadar) {
        this.hasRadar = hasRadar;
    }
}
