package com.ygsoft.apps.hc;

public enum HcLabelsGarage {
    L_GARAGE_NAME    ("שם המוסך"),
    L_GARAGE_PHONE   ("מספר טלפון"),
    L_GARAGE_CONTACT ("איש קשר"),
    L_GARAGE_LOCATION("מיקום"),
    ;

    private final String theText;


    public String getText() {
        return this.theText;
    }



    HcLabelsGarage(String text) {
        this.theText = text;
    }
}
