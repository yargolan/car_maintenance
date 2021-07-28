package com.ygsoft.apps.maintenance;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class TestGarage {

    @Test
    public void testGarageDetails() {
        Garage g = new Garage("Garage name", "Garage location", "054-2484460", "Moshe Levi");
        assertNotNull(g);
        g.toScreen();
    }
}
