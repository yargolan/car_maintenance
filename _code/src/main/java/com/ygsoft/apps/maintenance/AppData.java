package com.ygsoft.apps.maintenance;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import com.google.gson.Gson;



public class AppData {

    private static AppData single_instance = null;

    private final Gson gson = new Gson();
    private final HashMap<String, String> data = new HashMap<>();


    private AppData() {

        String initial_data_file = "";

        try {
            initial_data_file = String.format(
                    "%s%s%s%s",
                    new File("..").getCanonicalFile().getAbsolutePath(),
                    File.separatorChar,
                    "_config",
                    "initial_data.json"
            );
        }
        catch (IOException ioe) {
            initial_data_file = null;
        }


        data.put("initial_data_file", initial_data_file);
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











//    private void addDataFromConfigFile(String appConfigFile) {
//        try {
//
//            Reader reader = Files.newBufferedReader(Paths.get(appConfigFile));
//
//            JsonObject joAll = gson.fromJson(reader, JsonObject.class);
//
//            data.put("data_dir",                joAll.get("directories").getAsJsonObject().get("data_folder").getAsString());
//            data.put("user_messages_dir",       joAll.get("directories").getAsJsonObject().get("user_messages").getAsString());
//            data.put("initial_folders",         joAll.get("directories").getAsJsonObject().get("initial_folders").getAsString());
//
//            data.put("db_garages_file",         joAll.get("files").getAsJsonObject().get("db_garages").getAsString());
//            data.put("user_messages_file_name", joAll.get("files").getAsJsonObject().get("user_messages").getAsString());
//
//
//            data.put("db_garages",
//                    data.getOrDefault("data_dir", "")
//                    + File.separatorChar
//                    + data.getOrDefault("db_garages_file", "")
//            );
//        }
//        catch (IOException e) {
//            data.put("user_messages_file", "N/A");
//        }
//    }
//
//
//
//    public String getUserMessagesFileFullPath() {
//        return
//            data.getOrDefault("user_messages_dir", "")
//            + "/"
//            + data.getOrDefault("user_messages_file_name", "")
//        ;
//    }
//
//
//
//    public File getGaragesDatabaseFile() {
//        return new File(data.getOrDefault("db_garages", ""));
//    }
//
//
//    public String getInitialFolder() {
//        return data.getOrDefault("initial_folders", "");
//    }
//

}
