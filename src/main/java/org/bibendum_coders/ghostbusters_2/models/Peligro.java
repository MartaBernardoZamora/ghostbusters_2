package org.bibendum_coders.ghostbusters_2.models;

public enum Peligro {
    BAJO("BAJO"),
    MEDIO("MEDIO"),
    ALTO("ALTO");
    
    private final String peligroString;

    Peligro(String peligroString) {
        this.peligroString = peligroString;
    }

    
    public String getPeligroString() {
        return peligroString;
    }
}
