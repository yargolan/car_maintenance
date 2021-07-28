package com.ygsoft.apps.maintenance;

public class GarageAlreadyExistsException extends Exception {

    public GarageAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
