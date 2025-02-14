package org.bibendum_coders.ghostbusters_2.controllers;

import java.util.List;

import org.bibendum_coders.ghostbusters_2.models.CazadorModel;
import org.bibendum_coders.ghostbusters_2.models.FantasmaModel;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class CazadorControllerTest {
    @Test
    void testCapturarFantasma() {
        CazadorController cazadorController = new CazadorController();
        cazadorController.capturarFantasma("fantasmico", 1, "Medio", "vuela");

        List<FantasmaModel> result = cazadorController.getCazadorModel().getFantasmas();

        assertThat(result.size(), is(1));

    }

    @Test
    void testGetCazadorModel() {
        CazadorController cazadorController = new CazadorController();

        assertThat(cazadorController.getCazadorModel(), instanceOf(CazadorModel.class));
    }

    @Test
    void testGetContadorId() {
        CazadorController cazadorController = new CazadorController();

        assertThat(cazadorController.getContadorId(), is(1));
        cazadorController.capturarFantasma("fantasmico", 1, "Medio", "vuela");

        assertThat(cazadorController.getContadorId(), is(2));

    }

    @Test
    void testLiberarFantasma() {

    }

    @Test
    void testManejarMenu() {

    }

    @Test
    void testPrintMenuView() {

    }
}
