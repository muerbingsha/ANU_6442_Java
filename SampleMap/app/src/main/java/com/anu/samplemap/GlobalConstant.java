package com.anu.samplemap;

public enum GlobalConstant {

    LAT("latitude"),
    LON("longititude");


    private String name;

    GlobalConstant(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
