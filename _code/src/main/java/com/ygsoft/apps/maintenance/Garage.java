package com.ygsoft.apps.maintenance;


public class Garage {
    private final String name, location, phone, contact;

    public Garage(String name, String location, String phone, String contact) {
        this.name     = name;
        this.phone    = phone;
        this.contact  = contact;
        this.location = location;
    }


    public String getName() {
        return name;
    }


    public String getLocation() {
        return location;
    }


    public String getPhone() {
        return phone;
    }


    public String getContact() {
        return contact;
    }


    public void toScreen() {
        System.out.println("+--------------------------------");
        System.out.println("| Garage name     : " + this.getName());
        System.out.println("| Garage phone    : " + this.getPhone());
        System.out.println("| Garage contact  : " + this.getContact());
        System.out.println("| Garage Location : " + this.getLocation());
        System.out.println("+--------------------------------");
    }
}
