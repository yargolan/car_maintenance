package com.ygsoft.apps.cars;

import java.io.File;
import java.io.IOException;
import javax.swing.*;
import com.ygsoft.apps.Messages;
import com.ygsoft.apps.cars.ui.CarMaintenanceUi;



public class CarMaintenance {

    private final AppData appData = AppData.getInstance();


    public static void main(String[] args) {

        CarMaintenance carMaintenance = new CarMaintenance();
        carMaintenance.initApp();

        CarMaintenanceUi ui = new CarMaintenanceUi();
        ui.setAndShowUi();
    }


    private CarMaintenance() {
    }


    private void initApp() {

        // Set UI LNF
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.err.println("Cannot set the LNF. Sorry.");
        }


        // Verify needed folder.
        initFolders();
    }


    private void initFolders() {

        String[] initialFolders = appData.getInitialFolder().split(",");

        for (String folder : initialFolders) {

            File initialFolderFullPath;
            try {
                initialFolderFullPath = new File(String.format("%s%s%s",
                        new File(".").getCanonicalPath(),
                        File.separatorChar,
                        folder
                ));
                if (! initialFolderFullPath.exists()) {
                    if (!initialFolderFullPath.mkdirs()) {
                        Messages.exitWithError(
                                String.format("Cannot create the folder '%s'",
                                        initialFolderFullPath.getAbsolutePath()), true
                        );
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
