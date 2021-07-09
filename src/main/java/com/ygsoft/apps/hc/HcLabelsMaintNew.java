package com.ygsoft.apps.hc;

public enum HcLabelsMaintNew {

    L_DATE       ("תאריך הטיפול / תיקון"),
    L_TYPE       ("סוג הטיפול / תיקון"),
    L_DETAILS    ("פרטי הטיפול / תיקון"),
    L_GARAGE_NAME("שם המוסך"),

    L_DATE_DAY  ("יום"),
    L_DATE_YEAR ("שנה"),
    L_DATE_MONTH("חודש"),

    B_TODAY("היום"),
    B_APPROVE("אישור"),
    ;

    private final String theText;


    public String getText() {
        return this.theText;
    }



    HcLabelsMaintNew(String text) {
        this.theText = text;
    }
}
