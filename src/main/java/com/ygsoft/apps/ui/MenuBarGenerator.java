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
        JMenuItem mi0 = menuItemList.get(0);
        JMenuItem mi1 = menuItemList.get(1);
        mi0.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        mi1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        mMaintenance.add(mi0);
        mMaintenance.addSeparator();
        mMaintenance.add(mi1);


        // Garages
        JMenuItem mi2 = menuItemList.get(2);
        JMenuItem mi3 = menuItemList.get(3);
        JMenuItem mi4 = menuItemList.get(4);
        mi2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        mi3.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        mi4.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        mGarages.add(mi2);
        mGarages.add(mi3);
        mGarages.add(mi4);



        // Reports
        JMenuItem mi5 = menuItemList.get(5);
        JMenuItem mi6 = menuItemList.get(6);
        JMenuItem mi7 = menuItemList.get(7);
        mi5.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        mi6.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        mi7.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        mReports.add(mi7);
        mReports.add(mi5);
        mReports.add(mi6);


        // Add the menus to the menuBar
        mb.add(mMaintenance);
        mb.add(mGarages);
        mb.add(mReports);

        mb.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        return mb;
    }
}
