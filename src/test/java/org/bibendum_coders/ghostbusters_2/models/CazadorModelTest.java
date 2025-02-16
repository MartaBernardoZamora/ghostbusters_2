package org.bibendum_coders.ghostbusters_2.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.CoreMatchers.is;

public class CazadorModelTest {
    private CazadorModel cazador;

    @BeforeEach
    public void setUp() {
        cazador = new CazadorModel();
    }
    
    @Test
    void testAddFantasma() {
        FantasmaModel fantasmaModel = new FantasmaModel(1, "fantasmico", Clase.CLASE4, "medio", "vuela");
        cazador.addFantasma(fantasmaModel);
        List<FantasmaModel> result = cazador.getFantasmas();
        assertThat(result.size(), is(1));
    }

    @Test
    void testDeleteFantasma() {
        FantasmaModel fantasmaModel = new FantasmaModel(1, "fantasmico", Clase.CLASE4, "medio", "vuela");
        cazador.addFantasma(fantasmaModel);
        int fantasmaNumber = 1;
        cazador.deleteFantasma(fantasmaNumber-1);
        List<FantasmaModel> result = cazador.getFantasmas();
        assertThat(result.size(), is(0));
    }

    @Test
    void testGetContadorId() {
        int result = cazador.getContadorId();
        assertThat(result, is(1));

    }

    @Test
    void testGetFantasmas() {
        FantasmaModel fantasmaModel = new FantasmaModel(1, "fantasmico", Clase.CLASE4, "medio", "vuela");
        List<FantasmaModel> result = cazador.getFantasmas();
        List<FantasmaModel> resultExpected = new ArrayList<>();
        assertThat(result, is(resultExpected));
        cazador.addFantasma(fantasmaModel);
        result = cazador.getFantasmas();
        assertThat(result.size(), is(1));
        assertTrue(result.contains(fantasmaModel));

    }
}
