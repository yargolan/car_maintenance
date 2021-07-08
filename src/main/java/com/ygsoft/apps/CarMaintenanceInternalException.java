package com.ygsoft.apps;

import java.io.IOException;

public class CarMaintenanceInternalException extends Exception {

    
    public CarMaintenanceInternalException(String errorMessage) {
        super(errorMessage);
    }


    public CarMaintenanceInternalException(String errorMessage, Throwable t) {
        super(errorMessage);
    }
}
