package com.ygsoft.apps.maintenance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.ygsoft.apps.maintenance.hc.HcUserMessages;
import com.ygsoft.common.Messages;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class GarageWrapper {

    private final Garage g;
    private final AppData appData = AppData.getInstance();


    public GarageWrapper(Garage garage) {
        this.g = garage;
    }


    public void add() throws GarageAlreadyExistsException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

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
}
