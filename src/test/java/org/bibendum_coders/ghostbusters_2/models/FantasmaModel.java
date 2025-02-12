package org.bibendum_coders.ghostbusters_2.models;

import java.time.LocalDateTime;

public class FantasmaModel {
    private String nombre;
    private Clase clase;//Esto lo he cambiado para utilizar el enum Clase
    private String nivel;//¿Lo cambiamos a enum?
    private String habilidad;
    private LocalDateTime fechaCaptura;

    public FantasmaModel(String nombre, Clase clase, String nivel, String habilidad) {
        this.nombre = nombre;
        this.clase = clase;
        this.nivel=nivel;
        this.habilidad = habilidad;
        this.fechaCaptura = LocalDateTime.now();
    }

    public String getNombre() { return nombre; }
    public Clase getClase() { return clase; }
    public String getNivelPeligro() { return nivel; }
    public String getHabilidad() { return habilidad; }
    public LocalDateTime getFechaCaptura() { return fechaCaptura; }

}
