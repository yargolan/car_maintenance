package com.ygsoft.apps.hc;

public enum HcUserMessages {

    M_R_U_SURE("האם את/ה בטוח/ה ?"),
    ;

    private final String theText;


    public String getText() {
        return this.theText;
    }



    HcUserMessages(String text) {
        this.theText = text;
    }
}
