package org.bibendum_coders.ghostbusters_2.models;

import java.time.LocalDateTime;

public class FantasmaModel {
    private String nombre;
    private String clase;
    private String nivelPeligro;
    private String habilidad;
    private LocalDateTime fechaCaptura;

    public FantasmaModel(String nombre, String clase, String nivelPeligro, String habilidad) {
        this.nombre = nombre;
        this.clase = clase;
        this.nivelPeligro = nivelPeligro;
        this.habilidad = habilidad;
        this.fechaCaptura = LocalDateTime.now();
    }

    public String getNombre() { return nombre; }
    public String getClase() { return clase; }
    public String getNivelPeligro() { return nivelPeligro; }
    public String getHabilidad() { return habilidad; }
    public LocalDateTime getFechaCaptura() { return fechaCaptura; }

}
