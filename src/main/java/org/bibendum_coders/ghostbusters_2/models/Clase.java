package org.bibendum_coders.ghostbusters_2.models;

public enum Clase {    
    CLASE1("Clase I - Manifestación menor"),
    CLASE2("Clase II - Aparición móvil"),
    CLASE3("Clase III - Entidad inteligente"),
    CLASE4("Clase IV - Fantasma histórico"),
    CLASE5("Clase V - Espíritu antropomorfo"),
    CLASE6("Clase VI - Espíritu demoníaco"),
    CLASE7("Clase VII - Entidad ultraterrena");

    private final String claseString;

    Clase(String claseString) {
        this.claseString = claseString;
    }

    public String getClaseString() {
        return claseString;
    }
}
