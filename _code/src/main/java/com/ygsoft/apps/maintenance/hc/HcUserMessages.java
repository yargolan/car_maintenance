package com.ygsoft.apps.maintenance.hc;

public enum HcUserMessages {

    M_R_U_SURE            ("האם את/ה בטוח/ה ?"),
    M_MAINT_ADD_OK        ("הטיפול נרשם בהצלחה"),
    M_GARAGE_ADD_OK       ("המוסך נוסף לרשימה בהצלחה"),
    M_GARAGE_ALREADY_EXIST("המוסך כבר קיים ברשימה"),
    ;

    private final String theText;


    public String getText() {
        return this.theText;
    }



    HcUserMessages(String text) {
        this.theText = text;
    }
}