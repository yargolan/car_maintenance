package com.ygsoft.apps.maintenance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maintenance {

    private String maintDetails;

    private final String date;
    private final String garageName;
    private final String maintType;
    private final String speedometer;
    private static final AppData appData = AppData.getInstance();


    /**
     *
     * @param date - When the maintenance happened
     * @param garageName - Where it took place
     * @param maintType - Which category
     * @param maintDetails - Details of what was done
     */
    public Maintenance(String date, String garageName, String maintType, String speedometer, String maintDetails) {
        this.date         = date;
        this.maintType    = maintType;
        this.garageName   = garageName;
        this.speedometer  = speedometer;
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


    public String getSpeedometer() { return speedometer; }


    public void toScreen() {

        List<String> list = new ArrayList<>();

        String details = getMaintDetails();

        if (details.contains(appData.getTextDelimiter())) {
            list.addAll(Arrays.asList(details.split(appData.getTextDelimiter())));
        }
        else {
            list.add(details);
        }

        System.out.println("+----------------------------------------");
        System.out.println("| Date          : " + getDate());
        System.out.println("| Name          : " + getGarageName());
        System.out.println("| Type          : " + getMaintType());
        System.out.println("| Speedometer   : " + getSpeedometer());
        System.out.println("| Maint. Details: ");
        for (String s : list) {
            System.out.println("+---> " + s);
        }
        System.out.println("+---------------------------------------");
        System.out.println();
    }


    public void append(String readMaintDetails) {
        this.maintDetails +=
                appData.getTextDelimiter()
                + readMaintDetails;
    }
}














