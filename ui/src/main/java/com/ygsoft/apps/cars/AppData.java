package com.ygsoft.apps.cars;

import java.io.File;
import java.io.Reader;
import java.io.IOException;
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

            data.put("data_dir", joAll
                    .get("directories")
                    .getAsJsonObject()
                    .get("data_folder")
                    .getAsString()
            );
            data.put("common_dir", joAll
                    .get("directories")
                    .getAsJsonObject()
                    .get("common_folder")
                    .getAsString()
            );
            data.put("initial_folders", joAll
                    .get("directories")
                    .getAsJsonObject()
                    .get("initial_folders")
                    .getAsString()
            );
            data.put("user_messages_dir", joAll
                    .get("directories")
                    .getAsJsonObject()
                    .get("user_messages")
                    .getAsString()
            );

            data.put("user_messages", joAll
                    .get("files")
                    .getAsJsonObject()
                    .get("common_messages_file")
                    .getAsString()
            );
            data.put("db_garages_file", joAll
                    .get("files")
                    .getAsJsonObject()
                    .get("db_garages")
                    .getAsString()
            );
            data.put("user_messages_file_name", joAll
                    .get("files")
                    .getAsJsonObject()
                    .get("user_messages")
                    .getAsString()
            );
            data.put("db_garages",
                    data.getOrDefault("data_dir", "")
                    + File.separatorChar
                    + data.getOrDefault("db_garages_file", "")
            );
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



    public File getGaragesDatabaseFile() {
        return new File(data.getOrDefault("db_garages", ""));
    }



    public String getInitialFolder() {
        return data.getOrDefault("initial_folders", "");
    }
}
