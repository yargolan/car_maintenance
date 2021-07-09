package com.ygsoft.apps.ui;

import com.ygsoft.apps.hc.HcMenuTitles;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MenuBarGenerator {

    public MenuBarGenerator(){}


    public JMenuBar generate(List<JMenuItem> menuItemList) {

        JMenuBar mb = new JMenuBar();

        JMenu mMaintenance = new JMenu(HcMenuTitles.M_MAINTENANCE.getText());
        JMenu mGarages     = new JMenu(HcMenuTitles.M_GARAGES.getText());
        JMenu mReports     = new JMenu(HcMenuTitles.M_REPORTS.getText());

        // Maintenance
        mMaintenance.add(menuItemList.get(0));
        mMaintenance.addSeparator();
        mMaintenance.add(menuItemList.get(1));


        // Garages
        mGarages.add(menuItemList.get(2));
        mGarages.add(menuItemList.get(3));
        mGarages.add(menuItemList.get(4));


        // Reports
        mReports.add(menuItemList.get(7));
        mReports.add(menuItemList.get(5));
        mReports.add(menuItemList.get(6));


        // Add the menus to the menuBar
        mb.add(mMaintenance);
        mb.add(mGarages);
        mb.add(mReports);

        return mb;
    }
}
