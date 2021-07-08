package com.ygsoft.apps.ui;

import com.ygsoft.apps.hc.HcMenuTitles;

import javax.swing.*;
import java.util.List;

public class MenuBarGenerator {

    public MenuBarGenerator(){}


    public JMenuBar generate(List<JMenuItem> menuItemList) {

        JMenuBar mb = new JMenuBar();

        JMenu mMaintenance = new JMenu(HcMenuTitles.M_MAINTENANCE.getText());
        mMaintenance.add(menuItemList.get(0));
        mMaintenance.addSeparator();
        mMaintenance.add(menuItemList.get(1));

        mb.add(mMaintenance);

        return mb;

    }
}
