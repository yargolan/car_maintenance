package com.ygsoft.apps.maintenance.hc;

public enum HcFramesTitles {

    T_FRAME_MAIN       ("ריכוז הוצאות על רכב"),
    T_FRAME_MAINT_NEW  ("הוספת טיפול / תיקון"),
    T_FRAME_NEW_GARAGE ("הוספת מוסך לרשימה"),
    T_FRAME_DEL_GARAGE ("מחיקת מוסך מהרשימה"),

    T_FRAME_REPORT_PER_GARAGE     ("יצירת דוח לפי שם מוסך"),
    T_FRAME_REPORT_PER_SPEEDOMETER("יצירת דוח לפי שם מוסך"),
    ;

    private final String theText;


    public String getText() {
        return this.theText;
    }



    HcFramesTitles(String text) {
        this.theText = text;
    }
}
