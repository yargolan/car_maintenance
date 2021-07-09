package com.ygsoft.apps.hc;

public enum HcLabelsMaintNew {

    L_DATE       ("תאריך הטיפול / תיקון"),
    L_TYPE       ("סוג הטיפול / תיקון"),
    L_DETAILS    ("פרטי הטיפול / תיקון"),

    L_DATE_DAY  ("יום"),
    L_DATE_YEAR ("שנה"),
    L_DATE_MONTH("חודש"),

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
