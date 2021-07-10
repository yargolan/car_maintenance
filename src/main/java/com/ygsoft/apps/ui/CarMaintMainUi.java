package com.ygsoft.apps.ui;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import com.ygsoft.apps.hc.*;
import com.ygsoft.apps.Messages;



public class CarMaintMainUi {

    // General
    private JFrame fMain;
    private final UiWrapper uiWrapper   = new UiWrapper();


    // Menu items
    private final JMenuItem miMaintAdd          = new JMenuItem(HcMenuTitles.MI_MAINT_NEW.getText());
    private final JMenuItem miMaintExit         = new JMenuItem(HcMenuTitles.MI_MAINT_EXIT.getText());
    private final JMenuItem miGaragesAdd        = new JMenuItem(HcMenuTitles.MI_GARAGES_ADD.getText());
    private final JMenuItem miGaragesDel        = new JMenuItem(HcMenuTitles.MI_GARAGES_DEL.getText());
    private final JMenuItem miGaragesEdit       = new JMenuItem(HcMenuTitles.MI_GARAGES_EDIT.getText());
    private final JMenuItem miReportPerType     = new JMenuItem(HcMenuTitles.MI_REPORTS_PER_TYPE.getText());
    private final JMenuItem miReportPerDates    = new JMenuItem(HcMenuTitles.MI_REPORTS_PER_DATES.getText());
    private final JMenuItem miReportPerGarage   = new JMenuItem(HcMenuTitles.MI_REPORTS_PER_GARAGE.getText());
    private final List<JMenuItem> menuItemsList = new ArrayList<>();


    public CarMaintMainUi(){}



    private void setMenuItems() {
        menuItemsList.add(miMaintAdd);
        menuItemsList.add(miMaintExit);
        menuItemsList.add(miGaragesAdd);
        menuItemsList.add(miGaragesDel);
        menuItemsList.add(miGaragesEdit);
        menuItemsList.add(miReportPerType);
        menuItemsList.add(miReportPerDates);
        menuItemsList.add(miReportPerGarage);
    }



    public void setAndShowUi() {

        setMenuItems();

        fMain = uiWrapper.generateFrame(
                UiWrapper.FRAME_SIZE_XL,
                WindowConstants.EXIT_ON_CLOSE,
                HcFramesTitles.T_FRAME_MAIN.getText()
        );

        MenuBarGenerator menuBarGenerator = new MenuBarGenerator();


        // Set the menu bar of the application
        JMenuBar mb = menuBarGenerator.generate(menuItemsList);


        // Set the menu bar in to the main frame.
        fMain.setJMenuBar(mb);


        // Set the action listeners for the menu items.
        setActionListeners();


        fMain.setVisible(true);

    }



    private void setActionListeners() {

        miMaintExit.addActionListener(e->{
            System.out.println(e.getActionCommand());
            if (Messages.areYouSure(HcUserMessages.M_R_U_SURE.getText())) {
                fMain.dispose();
            }
        });

        miMaintAdd.addActionListener(e->{
            System.out.println(e.getActionCommand());
            MaintenanceUi mui = new MaintenanceUi();
            mui.setNew();
        });

        miGaragesAdd.addActionListener(e->{
            System.out.println(e.getActionCommand());
            GarageUi garageUi = new GarageUi();
            garageUi.addNew();
        });

        miGaragesDel.addActionListener(e->System.out.println(e.getActionCommand()));

        miGaragesEdit.addActionListener(e->System.out.println(e.getActionCommand()));

        miReportPerGarage.addActionListener(e->{
            ReportsUi reportsUi = new ReportsUi();
            reportsUi.generateReportPerGarage();
        });
    }
}

















