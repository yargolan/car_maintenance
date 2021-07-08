package com.ygsoft.apps;

import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class AppHelper {

    public AppHelper(){}



    public List<String> getMaintTypes() {

        BufferedReader reader;

        List<String> list = new ArrayList<>();

        Gson gson = new Gson();

        try {
            reader = new BufferedReader(new FileReader("Config/initial_data.json"));
        }
        catch (IOException e) {
            return list;
        }


        JsonObject jo = gson.fromJson(reader, JsonElement.class).getAsJsonObject();
        JsonArray ja = jo.getAsJsonArray("maintenance_types");
        for (int i = 0; i < ja.size(); i++) {
            list.add(ja.get(i).getAsString());
        }

        return list;
    }
}
