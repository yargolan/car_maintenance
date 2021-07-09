package com.ygsoft.apps.hc;

public enum HcUserMessages {

    M_R_U_SURE     ("האם את/ה בטוח/ה ?"),
    M_GARAGE_ADD_OK("המוסך נוסף לרשימה בהצלחה"),
    M_MAINT_ADD_OK("הטיפול נרשם בהצלחה"),
    ;

    private final String theText;


    public String getText() {
        return this.theText;
    }



    HcUserMessages(String text) {
        this.theText = text;
    }
}
