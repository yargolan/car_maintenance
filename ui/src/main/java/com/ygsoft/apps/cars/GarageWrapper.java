package com.ygsoft.apps.cars;

import java.io.File;
import java.io.Reader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class GarageWrapper {

    private final AppData appData = AppData.getInstance();

    public GarageWrapper() {}


    public List<Garage> getGaragesList() {

        List<Garage> list = new ArrayList<>();

        File garagesDatabase = appData.getGaragesDatabaseFile();

        if (garagesDatabase.exists()) {

            Gson gson = new Gson();

            try {
                Reader reader = Files.newBufferedReader(Paths.get(garagesDatabase.getAbsolutePath()));

                JsonArray ja = gson.fromJson(reader, JsonArray.class);

                for (int i = 0; i < ja.size(); i++) {
                    JsonObject jo = ja.get(i).getAsJsonObject();
                    Garage g = new Garage(
                            jo.get("name").getAsString(),
                            jo.get("location").getAsString(),
                            jo.get("phone").getAsString(),
                            jo.get("contact").getAsString()
                    );
                    list.add(g);
                }
            }
            catch (IOException e) {
                return list;
            }
        }

        return list;
    }
}

