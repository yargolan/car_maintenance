package com.ygsoft.apps.maintenance;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.ygsoft.common.Messages;


public class MaintenanceWrapper {

    private final Maintenance m;
    private final AppData appData = AppData.getInstance();


    public MaintenanceWrapper(Maintenance maintenance) {
        this.m = maintenance;
    }



    public MaintenanceWrapper() {
        m = null;
    }



    public void add() {

        File dbMaint = appData.getDbMaintenance();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<Maintenance> allMaintenances = new ArrayList<>();

        if (dbMaint.exists()) {

            try {
                JsonReader reader = new JsonReader(new FileReader(dbMaint));
                Maintenance[] current = gson.fromJson(reader, Maintenance[].class);
                Collections.addAll(allMaintenances, current);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Add the new maintenance, if it doesn't exist yet.
        if (this.m != null) {
            for (Maintenance mm : allMaintenances) {
                if (mm.getSpeedometer().equals(this.m.getSpeedometer())) {
                    if (mm.getGarageName().equals(this.m.getGarageName())) {
                        return;
                    }
                }
            }
            allMaintenances.add(this.m);
        }


        // Dump the data into the file.
        try (FileWriter writer = new FileWriter(dbMaint)) {
            gson.toJson(allMaintenances, writer);
        }
        catch (IOException e) {
            Messages.showMessage(Messages.MESSAGE_ERR, "Cannot write to the maintenance file.");
        }
    }



    public List<Maintenance> getAllMaintenance() {

        List<Maintenance> list = new ArrayList<>();

        File dbMaintenances = appData.getDbMaintenance();

        if (dbMaintenances.exists()) {
            Gson gson = new Gson();

            JsonReader reader = null;

            try {
                reader = new JsonReader(new FileReader(dbMaintenances));
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            assert reader != null;
            Maintenance[] current = gson.fromJson(reader, Maintenance[].class);
            Collections.addAll(list, current);

        }
        else {
            Messages.showMessage(Messages.MESSAGE_INF, "No data for report creation.");
        }

        return list;
    }
}








