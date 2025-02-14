package org.bibendum_coders.ghostbusters_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;

class AppTest {
    @Test
    void testApp() {
        assertEquals(1, 1);
    }
    @Test 
    @DisplayName("Test app functionality") 
    void testMain() { 
        App.main(new String[]{}); 
    }
}
