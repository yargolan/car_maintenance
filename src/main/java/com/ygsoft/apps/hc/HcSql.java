package com.ygsoft.apps.hc;

public enum HcSql {

    TABLE_NAME_MAINT  ("tblMaintenance"),
    TABLE_NAME_GARAGES("tblGarages"),

    COLUMN_GARAGE_NAME    ("cGarageName"),
    COLUMN_GARAGE_PHONE   ("cGaragePhone"),
    COLUMN_GARAGE_CONTACT ("cGarageContact"),
    COLUMN_GARAGE_LOCATION("cGarageLocation"),

    COLUMN_MAINT_DATE        ("cMaintDate"),
    COLUMN_MAINT_TYPE        ("cMaintType"),
    COLUMN_MAINT_DETAILS     ("cMaintDetails"),
    COLUMN_MAINT_GARAGE_INDEX("cMaintGarageIndex"),
    ;


    private final String theText;


    public String getText() {
        return this.theText;
    }



    HcSql(String text) {
        this.theText = text;
    }
}
