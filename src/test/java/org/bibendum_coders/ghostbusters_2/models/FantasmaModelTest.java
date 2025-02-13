package org.bibendum_coders.ghostbusters_2.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;

public class FantasmaModelTest {
    private FantasmaModel fantasma;

    @BeforeEach
    void setUp() { // No puede retornar un valor
        int id = 1;
        String nombre = "Espíritu del pescador";
        Clase clase = Clase.CLASE1;
        String nivelPeligro = "Bajo";
        String habilidad = "Invisibilidad";

        fantasma = new FantasmaModel(id, nombre, clase, nivelPeligro, habilidad);
    }
    @Test
    public void testConstructor() {
        assertThat(fantasma, notNullValue());
    }
    @Test
    void testGetClase() {
        assertThat(fantasma.getClase(), equalTo(Clase.CLASE1));
    }

    @Test
    void testGetFechaCaptura() {
        assertThat(fantasma.getFechaCaptura(), equalTo(LocalDate.now()));
    }

    @Test
    void testGetHabilidad() {
        assertThat(fantasma.getHabilidad(), equalTo("Invisibilidad"));
    }

    @Test
    void testGetId() {
        assertThat(fantasma.getId(), equalTo(1));
    }

    @Test
    void testGetNivelPeligro() {
        assertThat(fantasma.getNivelPeligro(), equalTo("Bajo"));
    }

    @Test
    void testGetNombre() {
        assertThat(fantasma.getNombre(), equalTo("Espíritu del pescador"));
    }
}
