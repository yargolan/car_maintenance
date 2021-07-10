package com.ygsoft.apps.cars;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.JsonObject;



public class AppData {

    private static AppData single_instance = null;
    private final Gson gson = new Gson();
    private final HashMap<String, String> data = new HashMap<>();


    private AppData() {

        data.put("config_file", "./common/app_config.json");

        // Add data from the config file
        addDataFromConfigFile(data.getOrDefault("config_file", ""));
    }



    public static AppData getInstance() {
        if (single_instance == null) {
            single_instance = new AppData();
        }

        return single_instance;
    }



    private void addDataFromConfigFile(String appConfigFile) {

        try {

            Reader reader = Files.newBufferedReader(Paths.get(appConfigFile));

            JsonObject joAll = gson.fromJson(reader, JsonObject.class);

            data.put("user_messages_dir",       joAll.get("directories").getAsJsonObject().get("user_messages").getAsString());
            data.put("user_messages_file_name", joAll.get("files").getAsJsonObject().get("user_messages").getAsString());
        }
        catch (IOException e) {
            data.put("user_messages_file", "N/A");
        }
    }



    public String getUserMessagesFileFullPath() {
        return
            data.getOrDefault("user_messages_dir", "")
            + "/"
            + data.getOrDefault("user_messages_file_name", "")
        ;
    }
}
