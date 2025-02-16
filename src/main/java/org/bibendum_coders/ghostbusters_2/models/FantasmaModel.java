package org.bibendum_coders.ghostbusters_2.models;

import java.time.LocalDate;

public class FantasmaModel {
    private int id;
    private String nombre;
    private Clase clase;//Esto lo he cambiado para utilizar el enum Clase
    private String nivel;//¿Lo cambiamos a enum?
    private String habilidad;
    private LocalDate fechaCaptura;
    private int afinity;

    public FantasmaModel(int id, String nombre, Clase clase, String nivel, String habilidad) {
        this.id = id;
        this.nombre = nombre;
        this.clase = clase;
        this.nivel=nivel;
        this.habilidad = habilidad;
        this.fechaCaptura = LocalDate.now();
        this.afinity=afinityCalculatation();
    }
    public int afinityCalculatation() {
        int afinity = 10-clase.ordinal();
        if (nivel.equals("Alto")||nivel.equals("alto")) return afinity-2;
        if (nivel.equals("Medio")||nivel.equals("medio")) return afinity-1;
        return afinity;
    }
    public int getId() { return id;}
    public String getNombre() { return nombre; }
    public Clase getClase() { return clase; }
    public String getNivelPeligro() { return nivel; }
    public String getHabilidad() { return habilidad; }
    public LocalDate getFechaCaptura() { return fechaCaptura; }
    public int getAfinity() { return afinity; }

    

}
