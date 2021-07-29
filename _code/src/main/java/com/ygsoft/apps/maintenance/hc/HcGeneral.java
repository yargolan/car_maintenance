package com.ygsoft.apps.maintenance.hc;

public enum HcGeneral {
    B_DELETE ("מחק"),
    B_APPROVE("אישור"),
    ;

    private final String theText;


    public String getText() {
        return this.theText;
    }



    HcGeneral(String text) {
        this.theText = text;
    }
}
