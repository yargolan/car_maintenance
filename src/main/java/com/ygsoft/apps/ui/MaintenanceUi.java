package com.ygsoft.apps.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import com.ygsoft.apps.*;
import com.ygsoft.apps.hc.*;



public class MaintenanceUi {

    private final DataSingleton dataSingleton = DataSingleton.getInstance();
    private final UiWrapper uiWrapper = new UiWrapper();
    private List<String> garageNames = new ArrayList<>();

    public MaintenanceUi(){}



    private void getGarageNames() {
        SqlWrapper1 sqlWrapper1 = new SqlWrapper1(dataSingleton.getDbGarages());
        try {
            garageNames = sqlWrapper1.getGarageNames();
        }
        catch (CarMaintenanceInternalException e) {
            Messages.showMessage(Messages.MESSAGE_ERR, e.getMessage());
        }
    }






    public void setNew() {

        // Get the garage names.
        getGarageNames();


        // Get the maintenance types
        AppHelper appHelper = new AppHelper();
        List<String> maintTypes = appHelper.getMaintTypes();


        JFrame fMaintNew = uiWrapper.generateFrame(
                UiWrapper.FRAME_SIZE_M,
                WindowConstants.DISPOSE_ON_CLOSE,
                HcFramesTitles.T_FRAME_MAINT_NEW.getText()
        );

        JLabel lDate               = new JLabel(HcLabelsMaintNew.L_DATE.getText());
        JLabel lGarageName         = new JLabel(HcLabelsGarage.L_GARAGE_NAME.getText());
        JLabel lAddAnotherLine     = new JLabel(HcLabelsMaintNew.L_ADD_LINE.getText());
        JLabel lMaintenanceType    = new JLabel(HcLabelsMaintNew.L_TYPE.getText());
        JLabel lMaintenanceDetails = new JLabel(HcLabelsMaintNew.L_DETAILS.getText());

        JTextField tfDateDay       = new JTextField(HcLabelsMaintNew.L_DATE_DAY.getText());
        JTextField tfDateYear      = new JTextField(HcLabelsMaintNew.L_DATE_YEAR.getText());
        JTextField tfDateMonth     = new JTextField(HcLabelsMaintNew.L_DATE_MONTH.getText());
        JTextField tfMaintDetails  = new JTextField();

        JCheckBox cbAddAnother = new JCheckBox();

        JButton bToday   = new JButton(HcLabelsMaintNew.B_TODAY.getText());
        JButton bApprove = new JButton(HcGeneral.B_APPROVE.getText());

        JComboBox<String> ddMaintType  = new JComboBox<>();
        JComboBox<String> ddGarageName = new JComboBox<>();


        lDate.setBounds              (260, 20, 150, 20);
        lGarageName.setBounds        (320, 60, 150, 20);
        lMaintenanceType.setBounds   (275, 100,150, 20);
        lMaintenanceDetails.setBounds(265, 140,150, 20);
        lAddAnotherLine.setBounds    (280, 180,120,  20);

        tfDateDay.setBounds     (85, 20,50, 20);
        tfDateYear.setBounds    (195,20,50, 20);
        tfDateMonth.setBounds   (140,20,50, 20);
        tfMaintDetails.setBounds(10,140,245,20);

        bToday.setBounds  (10, 20, 60, 20);
        bApprove.setBounds(150,220,100,40);

        ddGarageName.setBounds(85, 60,170, 25);
        ddMaintType.setBounds (85,100,170, 25);

        cbAddAnother.setBounds(230, 175, 30, 30);

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
        container.add(lAddAnotherLine);
        container.add(lMaintenanceType);
        container.add(lMaintenanceDetails);
        container.add(tfDateDay);
        container.add(tfDateYear);
        container.add(tfDateMonth);
        container.add(tfMaintDetails);
        container.add(bToday);
        container.add(bApprove);
        container.add(ddMaintType);
        container.add(ddGarageName);
        container.add(cbAddAnother);


        // Action listeners
        bToday.addActionListener(e->{
            String date_day   = DateAndTime.getTodayDay();
            String date_year  = DateAndTime.getTodayYear();
            String date_month = DateAndTime.getTodayMonth();
            tfDateDay.setText(date_day);
            tfDateYear.setText(date_year);
            tfDateMonth.setText(date_month);
        });

        bApprove.addActionListener(e->{

            String readDateDay      = tfDateDay.getText();
            String readDateYear     = tfDateYear.getText();
            String readDateMonth    = tfDateMonth.getText();
            String readMaintType    = (String)ddMaintType.getSelectedItem();
            String readGarageName   = (String)ddGarageName.getSelectedItem();
            String readMaintDetails = tfMaintDetails.getText();

            // Read the form
            if (readDateDay == null || readDateDay.isEmpty() || readDateDay.equals(HcLabelsMaintNew.L_DATE_DAY.getText())) {
                Messages.showMessage(Messages.MESSAGE_ERR, HcErrors.E_DATE.getText());
                return;
            }

            if (readDateYear == null || readDateYear.isEmpty() || readDateYear.equals(HcLabelsMaintNew.L_DATE_YEAR.getText())) {
                Messages.showMessage(Messages.MESSAGE_ERR, HcErrors.E_DATE.getText());
                return;
            }

            if (readDateMonth == null || readDateMonth.isEmpty() || readDateMonth.equals(HcLabelsMaintNew.L_DATE_MONTH.getText())) {
                Messages.showMessage(Messages.MESSAGE_ERR, HcErrors.E_DATE.getText());
                return;
            }

            if (readMaintDetails == null || readMaintDetails.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HcErrors.E_MAINT.getText());
                return;
            }

            if (readGarageName == null || readGarageName.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HcErrors.E_GARAGE.getText());
                return;
            }

            DateAndTime dateAndTime = new DateAndTime();
            String date = String.join("/",
                    readDateDay,
                    dateAndTime.convertMonthToText(Integer.parseInt(readDateMonth)),
                    readDateYear
            );

            Maintenance m = new Maintenance(date, readGarageName, readMaintType, readMaintDetails);
            MaintenanceWrapper mw = new MaintenanceWrapper();
            try {
                mw.add(m);
            }
            catch (CarMaintenanceInternalException ie) {
                Messages.showMessage(Messages.MESSAGE_ERR, ie.getMessage());
                return;
            }

            Messages.showMessage(Messages.MESSAGE_INF, HcUserMessages.M_MAINT_ADD_OK.getText());

            // Close the form only if the 'add another' is NOT selected
            if (cbAddAnother.isSelected()) {
                tfMaintDetails.setText("");
            }
            else {
                fMaintNew.dispose();
            }
        });

        // Insert initial lists to the drop-down menus
        Collections.sort(garageNames);
        for (String s : garageNames) {
            ddGarageName.addItem(s);
        }

        Collections.sort(maintTypes);
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
