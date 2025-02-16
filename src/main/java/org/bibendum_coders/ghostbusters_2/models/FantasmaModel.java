package org.bibendum_coders.ghostbusters_2.models;

import java.time.LocalDate;

public class FantasmaModel {
    private int id;
    private String nombre;
    private Clase clase;
    private Peligro peligro;
    private String habilidad;
    private LocalDate fechaCaptura;
    private int afinity;

    public FantasmaModel(int id, String nombre, Clase clase, Peligro peligro, String habilidad) {
        this.id = id;
        this.nombre = nombre;
        this.clase = clase;
        this.peligro=peligro;
        this.habilidad = habilidad;
        this.fechaCaptura = LocalDate.now();
        this.afinity=afinityCalculatation();
    }
    public int afinityCalculatation() {
        int afinity = 10-clase.ordinal();
        if (peligro.equals(Peligro.ALTO)) return afinity-2;
        if (peligro.equals(Peligro.MEDIO)) return afinity-1;
        return afinity;
    }
    public int getId() { return id;}
    public String getNombre() { return nombre; }
    public Clase getClase() { return clase; }
    public Peligro getNivelPeligro() { return peligro; }
    public String getHabilidad() { return habilidad; }
    public LocalDate getFechaCaptura() { return fechaCaptura; }
    public int getAfinity() { return afinity; }
}