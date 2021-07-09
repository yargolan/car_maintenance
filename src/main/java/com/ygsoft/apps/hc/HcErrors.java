package com.ygsoft.apps.hc;

public enum HcErrors {
    E_DATE  ("שגיאה או תאריך לא חוקי"),
    E_MAINT ("לא הוכנסו פרטי טיפול/תיקון"),
    E_GARAGE("לא הוכנס שם המוסך"),

    E_GARAGE_NAME    ("לא הוכנס שם המוסך"),
    E_GARAGE_PHONE   ("לא הוכנס טלפון של המוסך"),
    E_GARAGE_CONTACT ("לא הוכנס איש קשר במוסך"),
    E_GARAGE_LOCATION("לא הוכנס מיקום המוסך"),
    ;

    private final String theText;


    public String getText() {
        return this.theText;
    }



    HcErrors(String text) {
        this.theText = text;
    }
}
