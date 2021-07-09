package com.ygsoft.apps;

public class Maintenance {

    private final String date, garageName, maintType, maintDetails;


    /**
     *
     * @param date - When the maintenance happened
     * @param garageName - Where it took place
     * @param maintType - Which category
     * @param maintDetails - Details of what was done
     */
    public Maintenance(String date, String garageName, String maintType, String maintDetails) {
        this.date         = date;
        this.maintType    = maintType;
        this.garageName   = garageName;
        this.maintDetails = maintDetails;
    }


    public String getDate() {
        return date;
    }


    public String getGarageName() {
        return garageName;
    }


    public String getMaintType() {
        return maintType;
    }


    public String getMaintDetails() {
        return maintDetails;
    }
}
