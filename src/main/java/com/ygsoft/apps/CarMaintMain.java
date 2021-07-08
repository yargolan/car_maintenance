package com.ygsoft.apps;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ygsoft.apps.hc.HcMenuTitles;
import com.ygsoft.apps.hc.HcUserMessages;
import com.ygsoft.apps.ui.UiWrapper;
import com.ygsoft.apps.hc.HcFramesTitles;
import com.ygsoft.apps.ui.MenuBarGenerator;



public class CarMaintMain {

    private final UiWrapper uiWrapper   = new UiWrapper();
    private final JMenuItem miMaintAdd  = new JMenuItem(HcMenuTitles.MI_MAINT_NEW.getText());
    private final JMenuItem miMaintExit = new JMenuItem(HcMenuTitles.MI_MAINT_EXIT.getText());
    private final List<JMenuItem> menuItemsList = new ArrayList<>();
    private JFrame fMain;


    private CarMaintMain(){}



    public static void main(String[] args) {
        try {
            CarMaintMain carMaintMain = new CarMaintMain();
            carMaintMain.initApp();
            carMaintMain.setMenuItems();
            carMaintMain.setAndShowUi();
        }
        catch (Throwable t) {
            Messages.exitWithError(t.getMessage(), true);
        }
    }



    private void initApp() throws CarMaintenanceInternalException {
        File data;
        try {
            data = new File("./Data").getCanonicalFile();
        }
        catch (IOException e) {
            throw new CarMaintenanceInternalException(e.getMessage(), e);
        }

        if (!data.exists()) {
            if(!data.mkdirs()) {
                Messages.exitWithError("Cannot create the 'data' folder. Abort", true);
            }
        }
    }



    private void setMenuItems() {
        menuItemsList.add(miMaintAdd);
        menuItemsList.add(miMaintExit);
    }

    

    private void setAndShowUi() {

        fMain = uiWrapper.generateFrame(
                UiWrapper.FRAME_SIZE_XL,
                WindowConstants.EXIT_ON_CLOSE,
                HcFramesTitles.T_FRAME_MAIN.getText()
        );

        MenuBarGenerator menuBarGenerator = new MenuBarGenerator();
        JMenuBar mb = menuBarGenerator.generate(menuItemsList);


        // Set the menu bar in to the main frame.
        fMain.setJMenuBar(mb);


        // Set the action listeners for the menu items.
        setActionListeners();


        fMain.setVisible(true);

    }



    private void setActionListeners() {

        miMaintExit.addActionListener(e->{
            if(Messages.areYouSure(HcUserMessages.M_R_U_SURE.getText())) {
                fMain.dispose();
            }
        });
    }
}

















