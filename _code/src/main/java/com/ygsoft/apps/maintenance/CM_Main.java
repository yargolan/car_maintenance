package com.ygsoft.apps.maintenance;

import javax.swing.*;
import com.ygsoft.apps.maintenance.ui.CarMaintenanceUi;
import com.ygsoft.common.Messages;

import java.io.File;


public class CM_Main {

    private CM_Main(){}



    public static void main(String[] args) {
        CM_Main cm_main = new CM_Main();
        cm_main.initFolders();
        cm_main.goForIt();
    }



    private void initFolders() {
        AppData appData = AppData.getInstance();
        File[] foldersList = appData.getInitialFolders();
        for (File f : foldersList) {
            if (! f.exists()) {
                if (!f.mkdirs()) {
                    Messages.exitWithError("Cannot create the folder '" + f.getName() + "'.", true);
                }
            }
        }
    }



    private void goForIt() {
        try {
            // Set nicer look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Set and show UI part.
            CarMaintenanceUi ui = new CarMaintenanceUi();
            ui.setAndShowUi();
        }
        catch (Throwable t) {
            Messages.exitWithError(t.getMessage(), true);
        }
    }
}
