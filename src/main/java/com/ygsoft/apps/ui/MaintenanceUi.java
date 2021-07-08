package com.ygsoft.apps.ui;

import javax.swing.*;
import com.ygsoft.apps.hc.HcFramesTitles;



public class MaintenanceUi {

    private final UiWrapper uiWrapper = new UiWrapper();

    public MaintenanceUi(){}



    public void setNew() {

        JFrame fMaintNew = uiWrapper.generateFrame(
                UiWrapper.FRAME_SIZE_M,
                WindowConstants.DISPOSE_ON_CLOSE,
                HcFramesTitles.T_FRAME_MAINT_NEW.getText()
        );

        fMaintNew.setVisible(true);
    }
}
