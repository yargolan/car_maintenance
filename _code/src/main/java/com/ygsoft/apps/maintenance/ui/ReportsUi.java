package com.ygsoft.apps.maintenance.ui;


import javax.swing.*;

import com.ygsoft.apps.maintenance.*;
import com.ygsoft.apps.maintenance.hc.HcButtons;
import com.ygsoft.apps.maintenance.hc.HcLabelsGarage;
import com.ygsoft.apps.maintenance.hc.HcLabelsMaintNew;
import com.ygsoft.common.ui.UiWrapper;
import com.ygsoft.apps.maintenance.hc.HcFramesTitles;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ReportsUi {

    private final UiWrapper uiWrapper = new UiWrapper();



    public ReportsUi(){}


    public static void main(String[] args) {
        ReportsUi ui = new ReportsUi();
        ui.generate(1);
    }



    public void generate(int index) {

        List<String> list = new ArrayList<>();

        JFrame fReport = uiWrapper.generateFrame(
                UiWrapper.FRAME_SIZE_S,
                WindowConstants.DISPOSE_ON_CLOSE
        );

        JLabel lFilterName = new JLabel();
        JComboBox<String> ddOptions = new JComboBox<>();
        ((JLabel)ddOptions.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);


        if (index == ReportsGenerator.REPORT_BY_GARAGE_NAME) {
            fReport.setTitle(HcFramesTitles.T_FRAME_REPORT_PER_GARAGE.getText());
            lFilterName.setText(HcLabelsGarage.L_GARAGE_NAME.getText());
            lFilterName.setBounds(220,20, 100, 20);
            ddOptions.setBounds  (70, 20, 130, 25);

            GarageWrapper gw = new GarageWrapper();
            list = gw.getGarageNames();
        }
        else if (index == ReportsGenerator.REPORT_BY_MAINT_TYPE) {
            fReport.setTitle(HcFramesTitles.T_FRAME_REPORT_PER_TYPE.getText());
            lFilterName.setText(HcLabelsMaintNew.L_TYPE.getText());
            lFilterName.setBounds(180,20, 120, 20);
            ddOptions.setBounds  (40, 20, 130, 25);

            AppData appData = AppData.getInstance();
            list = appData.getMaintenanceTypes();
        }
        else {
            fReport.setTitle("Unknown report type");
        }


        for (String s : list) {
            ddOptions.addItem(s);
        }


        JButton bGenerate = new JButton(HcButtons.B_GENERATE.getText());

        bGenerate.setBounds  (50, 180,180, 40);


        fReport.getContentPane().add(lFilterName);
        fReport.getContentPane().add(ddOptions);
        fReport.getContentPane().add(bGenerate);


        // Add a listener to the button
        bGenerate.addActionListener(e->{
            String readFilterValue = (String)ddOptions.getSelectedItem();
            ReportsGenerator reportsGenerator = new ReportsGenerator(index);
            reportsGenerator.generate(readFilterValue);
        });

        fReport.setVisible(true);

    }
}