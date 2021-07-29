package com.ygsoft.apps.maintenance.hc;

public enum HcButtons {
    B_EDIT    ("ערוך"),
    B_DELETE  ("מחק"),
    B_APPROVE ("אישור"),
    B_GENERATE("צור דוח"),
    ;

    private final String theText;


    public String getText() {
        return this.theText;
    }



    HcButtons(String text) {
        this.theText = text;
    }
}
