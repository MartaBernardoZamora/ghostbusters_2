package org.bibendum_coders.ghostbusters_2.models;

import java.time.LocalDate;

public class FantasmaModel {
    private int id;
    private String nombre;
    private Clase clase;//Esto lo he cambiado para utilizar el enum Clase
    private String nivel;//¿Lo cambiamos a enum?
    private String habilidad;
    private LocalDate fechaCaptura;

    public FantasmaModel(int id, String nombre, Clase clase, String nivel, String habilidad) {
        this.id = id;
        this.nombre = nombre;
        this.clase = clase;
        this.nivel=nivel;
        this.habilidad = habilidad;
        this.fechaCaptura = LocalDate.now();
    }
    
    public int getId() { return id;}
    public String getNombre() { return nombre; }
    public Clase getClase() { return clase; }
    public String getNivelPeligro() { return nivel; }
    public String getHabilidad() { return habilidad; }
    public LocalDate getFechaCaptura() { return fechaCaptura; }

}
