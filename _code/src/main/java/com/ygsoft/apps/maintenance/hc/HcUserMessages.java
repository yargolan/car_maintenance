package com.ygsoft.apps.maintenance.hc;

public enum HcUserMessages {

    M_R_U_SURE            ("האם את/ה בטוח/ה ?"),
    M_MAINT_ADD_OK        ("הטיפול נרשם בהצלחה"),
    M_GARAGE_ADD_OK       ("המוסך נוסף לרשימה בהצלחה"),
    M_GARAGE_ALREADY_EXIST("המוסך כבר קיים ברשימה"),

    M_GARAGE_DELETED_OK  ("שם המוסך נמחק בהצלחה"),
    M_GARAGE_DELETED_FAIL("שם המוסך לא הצליח להמחק"),

    M_NO_DATA_FOUND("אין מידע לחתך זה"),
    ;

    private final String theText;


    public String getText() {
        return this.theText;
    }



    HcUserMessages(String text) {
        this.theText = text;
    }
}
