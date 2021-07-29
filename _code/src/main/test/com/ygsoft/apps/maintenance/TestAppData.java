package com.ygsoft.apps.maintenance;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestAppData {

    AppData appData = AppData.getInstance();



    @Test
    public void testInitialDataFile() {
        assertNotNull(appData.getInitialDataFile());
    }



    @Test
    public void testAppConfigFile() {
        assertNotNull(appData.getAppConfigFile());
    }
}
