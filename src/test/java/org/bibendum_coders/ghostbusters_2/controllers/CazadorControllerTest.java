package org.bibendum_coders.ghostbusters_2.controllers;

import java.util.ArrayList;
import java.util.List;

import org.bibendum_coders.ghostbusters_2.models.CazadorModel;
import org.bibendum_coders.ghostbusters_2.models.FantasmaModel;
import org.bibendum_coders.ghostbusters_2.views.MenuPrincipalView;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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
        CazadorController cazadorController = new CazadorController();
        cazadorController.capturarFantasma("fantasmico", 1, "Medio", "vuela");
        
        cazadorController.liberarFantasma(999);
        List<FantasmaModel> result = cazadorController.getCazadorModel().getFantasmas();
        assertThat(result.size(), is(1));

        cazadorController.liberarFantasma(1);
        result = cazadorController.getCazadorModel().getFantasmas();
        assertThat(result.size(), is(0));

    }

    @Test
    void testManejarMenu() {
        CazadorController controller = spy(new CazadorController());
        controller.manejarMenu(1);
        verify(controller, times(1)).showCapturaFantasmaView();
        
        CazadorModel cazadorModelMock = mock(CazadorModel.class);
        List<FantasmaModel> fantasmasMock = new ArrayList<>();
        when(cazadorModelMock.getFantasmas()).thenReturn(fantasmasMock);
        controller = spy(new CazadorController());
        controller.manejarMenu(2);
        verify(controller, times(1)).showEditarFantasmasView(fantasmasMock);
    }
    @Test
    public void testSalirAplicacion() {
        final CazadorController controller = spy(new CazadorController());
        doThrow(new RuntimeException("Salida detectada")).when(controller).salir();
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            controller.manejarMenu(6);
        });
        verify(controller, times(1)).salir();
        assertEquals("Salida detectada", exception.getMessage());
    }
    @Test
    void testPrintMenuView() {
        CazadorController controller = new CazadorController();
        try (MockedStatic<MenuPrincipalView> mocked = mockStatic(MenuPrincipalView.class)) {
            controller.printMenuView();
            mocked.verify(() -> MenuPrincipalView.getInstance(controller), times(1));
        }

    }
}
