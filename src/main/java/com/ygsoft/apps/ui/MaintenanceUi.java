package com.ygsoft.apps.ui;

import javax.swing.*;

import com.ygsoft.apps.AppHelper;
import com.ygsoft.apps.DateAndTime;
import com.ygsoft.apps.Garage;
import com.ygsoft.apps.GarageWrapper;
import com.ygsoft.apps.hc.HcFramesTitles;
import com.ygsoft.apps.hc.HcLabelsMaintNew;

import java.awt.*;
import java.util.List;


public class MaintenanceUi {

    private final UiWrapper uiWrapper = new UiWrapper();

    public MaintenanceUi(){}



    public void setNew() {

        // Get the garages list.
        GarageWrapper gw = new GarageWrapper();
        List<Garage> garagesList = gw.getGaragesList();


        // Get the maintenance types
        AppHelper appHelper = new AppHelper();
        List<String> maintTypes = appHelper.getMaintTypes();


        JFrame fMaintNew = uiWrapper.generateFrame(
                UiWrapper.FRAME_SIZE_M,
                WindowConstants.DISPOSE_ON_CLOSE,
                HcFramesTitles.T_FRAME_MAINT_NEW.getText()
        );

        JLabel lDate               = new JLabel(HcLabelsMaintNew.L_DATE.getText());
        JLabel lGarageName         = new JLabel(HcLabelsMaintNew.L_GARAGE_NAME.getText());
        JLabel lMaintenanceType    = new JLabel(HcLabelsMaintNew.L_TYPE.getText());
        JLabel lMaintenanceDetails = new JLabel(HcLabelsMaintNew.L_DETAILS.getText());

        JTextField tfDateDay      = new JTextField(HcLabelsMaintNew.L_DATE_DAY.getText());
        JTextField tfDateYear     = new JTextField(HcLabelsMaintNew.L_DATE_YEAR.getText());
        JTextField tfDateMonth    = new JTextField(HcLabelsMaintNew.L_DATE_MONTH.getText());
        JTextField tfMaintDetails = new JTextField();

        JButton bToday = new JButton(HcLabelsMaintNew.B_TODAY.getText());

        JComboBox<String> ddMaintType  = new JComboBox<>();
        JComboBox<String> ddGarageName = new JComboBox<>();


        lDate.setBounds              (260, 20, 150, 20);
        lGarageName.setBounds        (320, 60, 150, 20);
        lMaintenanceType.setBounds   (275, 100,150, 20);
        lMaintenanceDetails.setBounds(265, 140,150, 20);

        tfDateDay.setBounds     (85, 20,50, 20);
        tfDateYear.setBounds    (195,20,50, 20);
        tfDateMonth.setBounds   (140,20,50, 20);
        tfMaintDetails.setBounds(10,140,245,20);

        bToday.setBounds(10, 20,60, 20);

        ddGarageName.setBounds(85, 60,170, 25);
        ddMaintType.setBounds (85,100,170, 25);

        tfDateDay.setHorizontalAlignment(SwingConstants.RIGHT);
        tfDateYear.setHorizontalAlignment(SwingConstants.RIGHT);
        tfDateMonth.setHorizontalAlignment(SwingConstants.RIGHT);
        tfMaintDetails.setHorizontalAlignment(SwingConstants.RIGHT);
        tfMaintDetails.setFocusable(true);
        ((JLabel) ddMaintType.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        ((JLabel) ddGarageName.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);

        Container container = fMaintNew.getContentPane();
        container.add(lDate);
        container.add(lGarageName);
        container.add(lMaintenanceType);
        container.add(lMaintenanceDetails);
        container.add(tfDateDay);
        container.add(tfDateYear);
        container.add(tfDateMonth);
        container.add(tfMaintDetails);
        container.add(bToday);
        container.add(ddMaintType);
        container.add(ddGarageName);


        // Action listeners
        bToday.addActionListener(e->{
            String date_day   = DateAndTime.getTodayDay();
            String date_year  = DateAndTime.getTodayYear();
            String date_month = DateAndTime.getTodayMonth();
            tfDateDay.setText(date_day);
            tfDateYear.setText(date_year);
            tfDateMonth.setText(date_month);
        });


        // Insert initial lists to the drop-down menus
//        for (Garage g : garagesList) {
//            ddGarageName.addItem(g.getName());
//        }

        for (String s : maintTypes) {
            ddMaintType.addItem(s);
            ddMaintType.setAlignmentX(Component.RIGHT_ALIGNMENT);
        }

        fMaintNew.setVisible(true);
    }


    public static void main(String[] args) {
        MaintenanceUi m = new MaintenanceUi();
        m.setNew();
    }
}
