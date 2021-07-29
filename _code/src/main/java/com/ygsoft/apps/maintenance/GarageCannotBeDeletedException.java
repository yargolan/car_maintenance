package com.ygsoft.apps.maintenance;

public class GarageCannotBeDeletedException extends Exception {

    public GarageCannotBeDeletedException(String errorMessage) {
        super(errorMessage);
    }
}
