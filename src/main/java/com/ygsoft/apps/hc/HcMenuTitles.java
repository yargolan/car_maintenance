package com.ygsoft.apps.hc;

public enum HcMenuTitles {

    M_MAINTENANCE("טיפולים"),
    M_GARAGES    ("מוסכים"),

    MI_MAINT_NEW("הוספת טיפול"),
    MI_MAINT_EXIT("יציאה"),

    MI_GARAGES_ADD ("הוסף מוסך לרשימה"),
    MI_GARAGES_DEL ("מחק מוסך מהרשימה"),
    MI_GARAGES_EDIT("ערוך פרטי מוסך"),
    ;

    private final String theText;


    public String getText() {
        return this.theText;
    }



    HcMenuTitles(String text) {
        this.theText = text;
    }
}
