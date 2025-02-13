package org.bibendum_coders.ghostbusters_2.models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.jupiter.api.Test;

public class ClaseTest {
    @Test
    void testGetClaseString() {
        Clase clase = Clase.CLASE1;
        assertThat(clase.getClaseString(), equalTo("Clase I - Manifestación menor"));
        clase = Clase.CLASE2;
        assertThat(clase.getClaseString(), equalTo("Clase II - Aparición móvil"));
        clase = Clase.CLASE3;
        assertThat(clase.getClaseString(), equalTo("Clase III - Entidad inteligente"));
        clase = Clase.CLASE4;
        assertThat(clase.getClaseString(), equalTo("Clase IV - Fantasma histórico"));
        clase = Clase.CLASE5;
        assertThat(clase.getClaseString(), equalTo("Clase V - Espíritu antropomorfo"));
        clase = Clase.CLASE6;
        assertThat(clase.getClaseString(), equalTo("Clase VI - Espíritu demoníaco"));
        clase = Clase.CLASE7;
        assertThat(clase.getClaseString(), equalTo("Clase VII - Entidad ultraterrena"));

    }
}
