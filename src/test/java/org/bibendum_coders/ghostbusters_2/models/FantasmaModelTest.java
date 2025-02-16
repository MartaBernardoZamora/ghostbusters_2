package org.bibendum_coders.ghostbusters_2.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

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
    @Test 
    void testAfinityCalculatation() {
        assertThat(fantasma.afinityCalculatation(), equalTo(10));
        fantasma = new FantasmaModel(2, "Espíritu del pescador", Clase.CLASE3, "Medio", "Invisibilidad");
        assertThat(fantasma.afinityCalculatation(), equalTo(7));
        fantasma = new FantasmaModel(3, "Espíritu del pescador", Clase.CLASE4, "Alto", "Invisibilidad");
        assertThat(fantasma.afinityCalculatation(), equalTo(5));
        fantasma = new FantasmaModel(4, "Espíritu del pescador", Clase.CLASE7, "Alto", "Invisibilidad");
        assertThat(fantasma.afinityCalculatation(), equalTo(2));
    }
}
