package com.ygsoft.apps.maintenance;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.ygsoft.common.Messages;
import com.ygsoft.apps.maintenance.hc.HcUserMessages;



public class GarageWrapper {

    private Garage g;
    private final AppData appData = AppData.getInstance();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final File dbGarages = appData.getDbGarages();


    public GarageWrapper(Garage garage) {
        this.g = garage;
    }



    public GarageWrapper() {}



    public void setGarage(Garage garage) {
        this.g = garage;
    }


    public void add() throws GarageAlreadyExistsException {

        List<Garage> allGarages = new ArrayList<>();

        if (dbGarages.exists()) {

            JsonReader reader = null;

            try {
                reader = new JsonReader(new FileReader(dbGarages));
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            assert reader != null;
            Garage[] current = gson.fromJson(reader, Garage[].class);
            Collections.addAll(allGarages, current);
        }


        // Add the new Garage, if it doesn't exist yet.
        for (Garage garage : allGarages) {
            if (garage.getName().equals(g.getName())) {
                throw new GarageAlreadyExistsException(
                        g.getName()
                        + "\n"
                        + HcUserMessages.M_GARAGE_ALREADY_EXIST.getText());
            }
        }

        allGarages.add(g);


        // Re-write the file.
        try (FileWriter writer = new FileWriter(dbGarages)) {
            gson.toJson(allGarages, writer);
        }
        catch (IOException e) {
            Messages.showMessage(Messages.MESSAGE_ERR, "Cannot write to the garages file.");
        }
    }



    public List<Garage> getGarages() {

        Gson gson = new Gson();

        File dbGarages = appData.getDbGarages();

        List<Garage> allGarages = new ArrayList<>();

        if (dbGarages.exists()) {

            JsonReader reader = null;

            try {
                reader = new JsonReader(new FileReader(dbGarages));
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            assert reader != null;
            Garage[] current = gson.fromJson(reader, Garage[].class);
            Collections.addAll(allGarages, current);
        }

        return allGarages;
    }



    public void remove() throws GarageCannotBeDeletedException {

        List<Garage> allGarages = getGarages();

        allGarages.removeIf(gg -> gg.getName().equals(this.g.getName()));

        // Re-write the file.
        try (FileWriter writer = new FileWriter(dbGarages)) {
            gson.toJson(allGarages, writer);
        }
        catch (IOException e) {
            Messages.showMessage(Messages.MESSAGE_ERR, "Cannot write to the garages file.");
        }
    }
}
