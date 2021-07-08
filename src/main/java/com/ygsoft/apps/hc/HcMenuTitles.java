package com.ygsoft.apps.hc;

public enum HcMenuTitles {

    M_MAINTENANCE("טיפולים"),

    MI_MAINT_NEW("הוספת טיפול"),
    MI_MAINT_EXIT("יציאה"),
    ;

    private final String theText;


    public String getText() {
        return this.theText;
    }



    HcMenuTitles(String text) {
        this.theText = text;
    }
}
