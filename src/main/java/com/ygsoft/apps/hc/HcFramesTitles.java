package com.ygsoft.apps.hc;

public enum HcFramesTitles {

    T_FRAME_MAIN     ("ריכוז הוצאות על רכב"),
    T_FRAME_MAINT_NEW("הוספת טיפול / תיקון"),
    ;

    private final String theText;


    public String getText() {
        return this.theText;
    }



    HcFramesTitles(String text) {
        this.theText = text;
    }
}