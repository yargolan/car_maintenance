package com.ygsoft.apps.maintenance;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;



public class AppData {

    private static AppData single_instance = null;

    private final HashMap<String, String> data = new HashMap<>();


    private AppData() {

        String initial_data_file;

        String rootFolder = "";

        try {
            rootFolder = new File("..").getCanonicalFile().getAbsolutePath();

            initial_data_file = String.format(
                    "%s%s%s%s",
                    rootFolder,
                    File.separatorChar,
                    "_config",
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



        data.put("db_garages",        dbGaragesFile.getAbsolutePath());
        data.put("root_folder",       rootFolder);
        data.put("app_config_file",   fAppConfig.getAbsolutePath());
        data.put("initial_data_file", initial_data_file);

        // Folders
        data.put("databases_folder", dirDatabases.getAbsolutePath());
    }



    public static AppData getInstance() {
        if (single_instance == null) {
            single_instance = new AppData();
        }

        return single_instance;
    }


    // +------------------
    // |  Getters
    // +------------------
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
}
