package com.ygsoft.apps.maintenance.hc;


public enum HcMenuTitles {

    M_MAINTENANCE("טיפולים"),
    M_GARAGES    ("מוסכים"),
    M_REPORTS    ("דוחות"),

    MI_MAINT_NEW("הוספת טיפול"),
    MI_MAINT_EXIT("יציאה"),

    MI_GARAGES_ADD ("הוסף מוסך לרשימה"),
    MI_GARAGES_DEL ("מחק מוסך מהרשימה"),
    MI_GARAGES_EDIT("ערוך פרטי מוסך"),

    MI_REPORTS_PER_TYPE  ("לפי סוג הטיפול"),
    MI_REPORTS_PER_DATES ("לפי טווח תאריכים"),
    MI_REPORTS_PER_GARAGE("לפי שם מוסך"),
    ;

    private final String theText;


    public String getText() {
        return this.theText;
    }



    HcMenuTitles(String text) {
        this.theText = text;
    }
}
