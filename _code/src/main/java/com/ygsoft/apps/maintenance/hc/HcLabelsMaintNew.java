package com.ygsoft.apps.maintenance.hc;

public enum HcLabelsMaintNew {

    L_DATE       ("תאריך הטיפול / תיקון"),
    L_TYPE       ("סוג הטיפול / תיקון"),
    L_DETAILS    ("פרטי הטיפול / תיקון"),
    L_SPEEDOMETER("קריאת מונה"),

    L_DATE_DAY  ("יום"),
    L_DATE_YEAR ("שנה"),
    L_DATE_MONTH("חודש"),

    L_ADD_LINE  ("הוסף שורה נוספת"),

    B_TODAY("היום"),
    ;

    private final String theText;


    public String getText() {
        return this.theText;
    }



    HcLabelsMaintNew(String text) {
        this.theText = text;
    }
}
