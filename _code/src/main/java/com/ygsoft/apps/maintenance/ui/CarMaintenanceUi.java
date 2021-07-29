package com.ygsoft.apps.maintenance.ui;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import com.ygsoft.apps.maintenance.hc.*;
import com.ygsoft.common.*;
import com.ygsoft.common.ui.UiWrapper;


public class CarMaintenanceUi {

    private JFrame fMain;

    private final UiWrapper uiWrapper = new UiWrapper();

    // Menu items
    private final JMenuItem miMaintAdd          = new JMenuItem(HcMenuTitles.MI_MAINT_NEW.getText());
    private final JMenuItem miMaintExit         = new JMenuItem(HcMenuTitles.MI_MAINT_EXIT.getText());
    private final JMenuItem miGaragesAdd        = new JMenuItem(HcMenuTitles.MI_GARAGES_ADD.getText());
    private final JMenuItem miGaragesDel        = new JMenuItem(HcMenuTitles.MI_GARAGES_DEL.getText());
    private final JMenuItem miGaragesEdit       = new JMenuItem(HcMenuTitles.MI_GARAGES_EDIT.getText());
    private final JMenuItem miReportPerType     = new JMenuItem(HcMenuTitles.MI_REPORTS_PER_TYPE.getText());
    private final JMenuItem miReportPerDates    = new JMenuItem(HcMenuTitles.MI_REPORTS_PER_DATES.getText());
    private final JMenuItem miReportPerGarage   = new JMenuItem(HcMenuTitles.MI_REPORTS_PER_GARAGE.getText());



    public CarMaintenanceUi() {}


    public void setAndShowUi() {

        List<JMenuItem> menuItemsList = Arrays.asList(
                miMaintAdd,
                miMaintExit,
                miGaragesAdd,
                miGaragesDel,
                miGaragesEdit,
                miReportPerType,
                miReportPerDates,
                miReportPerGarage
        );

        fMain = uiWrapper.generateFrame(
                UiWrapper.FRAME_SIZE_L,
                WindowConstants.EXIT_ON_CLOSE,
                HcFramesTitles.T_FRAME_MAIN.getText()
        );

        // Set the menu bar for the app.
        MenuBarGenerator menuBarGenerator = new MenuBarGenerator();
        JMenuBar mb = menuBarGenerator.generate(menuItemsList);
        fMain.setJMenuBar(mb);


        // Set action listeners to the menu items.
        setActionListeners();


        fMain.setVisible(true);
    }



    private void setActionListeners() {
        miMaintExit.addActionListener(e->{
            if (Messages.areYouSure(HcUserMessages.M_R_U_SURE.getText())) {
                fMain.dispose();
            }
        });

        miMaintAdd.addActionListener(e->{
            System.out.println(e.getActionCommand());
            MaintenanceUi mui = new MaintenanceUi();
            mui.addNew();
        });

        miGaragesAdd.addActionListener(e->{
            System.out.println(e.getActionCommand());
            GarageUi garageUi = new GarageUi();
            garageUi.addNew();
        });

        miGaragesDel.addActionListener(e-> {
            System.out.println(e.getActionCommand());
            GarageUi garageUi = new GarageUi();
            garageUi.remove();
        });
    }
}
