package com.ygsoft.apps.ui;

import java.awt.*;
import java.util.ArrayList;
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
        SqlWrapper sqlWrapper = new SqlWrapper(dataSingleton.getDbGarages());
        try {
            garageNames = sqlWrapper.getGarageNames();
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
        JLabel lGarageName         = new JLabel(HcLabelsMaintNew.L_GARAGE_NAME.getText());
        JLabel lMaintenanceType    = new JLabel(HcLabelsMaintNew.L_TYPE.getText());
        JLabel lMaintenanceDetails = new JLabel(HcLabelsMaintNew.L_DETAILS.getText());

        JTextField tfDateDay       = new JTextField(HcLabelsMaintNew.L_DATE_DAY.getText());
        JTextField tfDateYear      = new JTextField(HcLabelsMaintNew.L_DATE_YEAR.getText());
        JTextField tfDateMonth     = new JTextField(HcLabelsMaintNew.L_DATE_MONTH.getText());
        JTextField tfMaintDetails  = new JTextField();

        JButton bToday   = new JButton(HcLabelsMaintNew.B_TODAY.getText());
        JButton bApprove = new JButton(HcLabelsMaintNew.B_APPROVE.getText());

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

        bToday.setBounds  (10, 20, 60, 20);
        bApprove.setBounds(150,210,100,40);

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
        container.add(bApprove);
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

            String date = readDateDay + "/" + readDateMonth + "/" + readDateYear;
            Maintenance m = new Maintenance(date, readGarageName, readMaintType, readMaintDetails);
            MaintenanceWrapper mw = new MaintenanceWrapper();
            try {
                mw.add(m);
            }
            catch (CarMaintenanceInternalException ie) {
                Messages.showMessage(Messages.MESSAGE_ERR, ie.getMessage());
            }
        });

        // Insert initial lists to the drop-down menus
        for (String s : garageNames) {
            ddGarageName.addItem(s);
        }

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
