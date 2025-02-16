package org.bibendum_coders.ghostbusters_2.models;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class PeligroTest {
    @Test
    void testGetPeligroString() {
        Peligro peligro = Peligro.BAJO;
        assertThat(peligro.getPeligroString(), equalTo("BAJO"));
        peligro = Peligro.MEDIO;
        assertThat(peligro.getPeligroString(), equalTo("MEDIO"));
        peligro = Peligro.ALTO;
        assertThat(peligro.getPeligroString(), equalTo("ALTO"));
    }
}
