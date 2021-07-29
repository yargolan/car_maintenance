package com.ygsoft.apps.maintenance;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AppData {

    private static AppData single_instance = null;
    private final HashMap<String, String> data = new HashMap<>();



    public static AppData getInstance() {
        if (single_instance == null) {
            single_instance = new AppData();
        }

        return single_instance;
    }




    private AppData() {

        // Load data from the app config file.
        load_app_config();


        // Set data for the application.
        set_application_data();
    }



    private void load_app_config() {

    }



    private void set_application_data() {



        String initial_data_file;

        String rootFolder = "";

        try {
            rootFolder = new File("..").getCanonicalFile().getAbsolutePath();

            initial_data_file = String.format(
                    "%s%s%s%s%s",
                    rootFolder,
                    File.separatorChar,
                    "_config",
                    File.separatorChar,
                    "initial_data.json"
            );
        }
        catch (IOException ioe) {
            initial_data_file = null;
        }

        File dirDatabases = new File(String.format("%s%s%s",
                rootFolder,
                File.separatorChar,
                "_data"
        ));



        File fAppConfig = new File(String.format("%s%s%s%s%s",
                rootFolder,
                File.separatorChar,
                "_config",
                File.separatorChar,
                "app_config.json"
        ));



        File dbGaragesFile = new File(String.format("%s%s%s",
                dirDatabases,
                File.separatorChar,
                "db_garages.json"
        ));



        File dbMaintFile = new File(String.format("%s%s%s",
                dirDatabases,
                File.separatorChar,
                "db_maintenance.json"
        ));


        // General
        data.put("text_delimiter", ":::");


        // Databases
        data.put("db_garages",     dbGaragesFile.getAbsolutePath());
        data.put("db_maintenance", dbMaintFile.getAbsolutePath());


        // Folders and files.
        data.put("root_folder",       rootFolder);
        data.put("app_config_file",   fAppConfig.getAbsolutePath());
        data.put("databases_folder",  dirDatabases.getAbsolutePath());
        data.put("initial_data_file", initial_data_file);
    }


    // +------------------
    // |  Getters
    // +------------------
    public String getTextDelimiter() {
        return data.get("text_delimiter");
    }


    public File getInitialDataFile() {
        return new File(data.get("initial_data_file"));
    }


    public File getAppConfigFile() {
        return new File(data.get("app_config_file"));
    }


    public File[] getInitialFolders() {

        return new File[]{
                new File(data.get("root_folder") + File.separatorChar + "_data"),
                new File(data.get("root_folder") + File.separatorChar + "_config")
        };
    }


    public File getDbGarages() {
        return new File(data.get("db_garages"));
    }


    public File getDbMaintenance() {
        return new File(data.get("db_maintenance"));
    }


    public List<String> getMaintenanceTypes() {

        List<String> list = new ArrayList<>();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(data.get("initial_data_file")));
            JsonObject jo = new Gson().fromJson(reader, JsonObject.class);
            JsonArray ja = jo.getAsJsonArray("maintenance_types");
            for (int i = 0; i < ja.size(); i++) {
                list.add(ja.get(i).getAsString());
            }
        }
        catch (IOException e) {
            return list;
        }

        return list;
    }
}












