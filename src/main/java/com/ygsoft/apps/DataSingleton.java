package com.ygsoft.apps;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import com.jcabi.manifests.Manifests;


public class DataSingleton {

    private static DataSingleton single_instance = null;
    private final HashMap<String, String> data = new HashMap<>();


    private DataSingleton() {

        try {
            File rootDir    = new File(".").getCanonicalFile();
            File DataDir    = new File(String.format("%s/Data", rootDir));
            File ConfigDir  = new File(String.format("%s/Config", rootDir));
            File ReportsDir = new File(String.format("%s/Reports", rootDir));

            data.put("root_dir",    rootDir.getAbsolutePath());
            data.put("data_dir",    DataDir.getAbsolutePath());
            data.put("config_dir" , ConfigDir.getAbsolutePath());
            data.put("reports_dir", ReportsDir.getAbsolutePath());


            data.put("db_maint" ,  "Maintenances.db");
            data.put("db_garages", "Garages.db");

            data.put("sql_separator", "::::");
        }
        catch (IOException e) {
            Messages.showMessage(Messages.MESSAGE_ERR, "Cannot determine the profiles dir.");
        }


        try {
            data.put("version", Manifests.read("version"));
        }
        catch (Exception e) {
            data.put("version", "Unknown");
        }
    }



    public String getSqlSeparator() {
        return data.getOrDefault("sql_separator", "Unknown");
    }


    public String getAppVersion() {
        return data.getOrDefault("version", "Unknown");
    }


    public File getRootDir() {
        return new File(data.getOrDefault("root_dir", "Unknown"));
    }



    public File getDataDir() {
        return new File(data.getOrDefault("data_dir", "Unknown"));
    }



    public File getReportsDir() {
        return new File(data.getOrDefault("reports_dir", "Unknown"));
    }


    public File getDbGarages() {
        return new File(String.format("%s%s%s",
                data.getOrDefault("data_dir", "Unknown"),
                File.separatorChar,
                data.getOrDefault("db_garages", "Unknown")
        ));
    }


    public File getDbMaint() {
        return new File(String.format("%s%s%s",
                data.getOrDefault("data_dir", "Unknown"),
                File.separatorChar,
                data.getOrDefault("db_maint", "Unknown")
        ));
    }



    public static DataSingleton getInstance() {
        if (single_instance == null) {
            single_instance = new DataSingleton();
        }

        return single_instance;
    }
}
