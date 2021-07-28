package com.ygsoft.apps.maintenance.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.ygsoft.common.*;
import com.ygsoft.common.ui.*;
import com.ygsoft.apps.maintenance.*;
import com.ygsoft.apps.maintenance.hc.*;





public class MaintenanceUi {

    private final GarageWrapper garageWrapper = new GarageWrapper();
    private final AppData appData = AppData.getInstance();
    private final UiWrapper uiWrapper = new UiWrapper();

    // UI components
    JFrame fMaintNew;
    JButton bToday, bApprove;
    JCheckBox cbAddAnother;
    JComboBox<String> ddMaintType  = new JComboBox<>();
    JComboBox<String> ddGarageName = new JComboBox<>();
    JTextField tfDateDay, tfDateYear, tfDateMonth, tfMaintDetails, tfSpeedometer;

    Maintenance maintenance = null;



    public MaintenanceUi(){}


    public static void main(String[] args) {
        MaintenanceUi m = new MaintenanceUi();
        m.addNew();
    }


    public void addNew() {

        // Get the list of garages.
        List<Garage> gList = garageWrapper.getGarages();


        // Get the list of maintenance types.
        List<String> mTypes = appData.getMaintenanceTypes();


        fMaintNew = uiWrapper.generateFrame(
                UiWrapper.FRAME_SIZE_L,
                WindowConstants.DISPOSE_ON_CLOSE,
                HcFramesTitles.T_FRAME_MAINT_NEW.getText()
        );

        fMaintNew.setVisible(true);

        JLabel lDate               = new JLabel(HcLabelsMaintNew.L_DATE.getText());
        JLabel lSpeedometer        = new JLabel(HcLabelsMaintNew.L_SPEEDOMETER.getText());
        JLabel lGarageName         = new JLabel(HcLabelsGarage.L_GARAGE_NAME.getText());
        JLabel lAddAnotherLine     = new JLabel(HcLabelsMaintNew.L_ADD_LINE.getText());
        JLabel lMaintenanceType    = new JLabel(HcLabelsMaintNew.L_TYPE.getText());
        JLabel lMaintenanceDetails = new JLabel(HcLabelsMaintNew.L_DETAILS.getText());

        tfDateDay      = new JTextField(HcLabelsMaintNew.L_DATE_DAY.getText());
        tfDateYear     = new JTextField(HcLabelsMaintNew.L_DATE_YEAR.getText());
        tfDateMonth    = new JTextField(HcLabelsMaintNew.L_DATE_MONTH.getText());
        tfSpeedometer  = new JTextField();
        tfMaintDetails = new JTextField();

        cbAddAnother = new JCheckBox();

        bToday = new JButton(HcLabelsMaintNew.B_TODAY.getText());
        bApprove = new JButton(HcGeneral.B_APPROVE.getText());

        lDate.setBounds              (360, 20, 150,20);
        lGarageName.setBounds        (420, 60, 150,20);
        lSpeedometer.setBounds       (415, 140,150,20);
        lMaintenanceType.setBounds   (375, 100,150,20);
        lAddAnotherLine.setBounds    (380, 220,120,20);
        lMaintenanceDetails.setBounds(365, 180,150,20);

        tfDateDay.setBounds     (185,20, 50, 20);
        tfDateYear.setBounds    (295,20, 50, 20);
        tfDateMonth.setBounds   (240,20, 50, 20);
        tfSpeedometer.setBounds (110,140,245,20);
        tfMaintDetails.setBounds(110,180,245,20);

        bToday.setBounds  (110, 20, 60, 20);
        bApprove.setBounds(200,280,100,40);

        ddMaintType.setBounds (185, 100, 170, 25);
        ddGarageName.setBounds(185, 60, 170, 25);

        cbAddAnother.setBounds(330, 210, 30, 30);

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
        container.add(lSpeedometer);
        container.add(lAddAnotherLine);
        container.add(lMaintenanceType);
        container.add(lMaintenanceDetails);
        container.add(tfDateDay);
        container.add(tfDateYear);
        container.add(tfDateMonth);
        container.add(tfSpeedometer);
        container.add(tfMaintDetails);
        container.add(bToday);
        container.add(bApprove);
        container.add(ddMaintType);
        container.add(ddGarageName);
        container.add(cbAddAnother);


        // Add action listeners - buttons
        addButtonsActionListeners();


        // Insert initial lists to the drop-down menus
        // TODO: Sort the list
        for (Garage g : gList) {
            ddGarageName.addItem(g.getName());
            ddGarageName.setAlignmentX(Component.RIGHT_ALIGNMENT);
        }


        // TODO: Sort the list
        for (String s : mTypes) {
            ddMaintType.addItem(s);
            ddMaintType.setAlignmentX(Component.RIGHT_ALIGNMENT);
        }

        fMaintNew.setVisible(true);
    }



    private void addButtonsActionListeners() {

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
            String readSpeedometer  = tfSpeedometer.getText();
            String readMaintDetails = tfMaintDetails.getText();



            // -----------------------
            // Read the form
            // -----------------------
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

            if (readSpeedometer == null || readSpeedometer.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HcErrors.E_NO_SPEEDOMETER.getText());
                return;
            }

            if (readMaintType == null || readMaintType.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HcErrors.E_MAINT_TYPE.getText());
                return;
            }

            DateAndTime dateAndTime = new DateAndTime();
            String fullDate = String.join("/",
                    readDateDay,
                    dateAndTime.convertMonthToText(Integer.parseInt(readDateMonth)),
                    readDateYear
            );


            if (maintenance == null) {
                maintenance = new Maintenance(fullDate, readGarageName, readMaintType, readSpeedometer, readMaintDetails);
            }
            else {
                maintenance.append(readMaintDetails);
            }


            if (cbAddAnother.isSelected()) {

                // Clear the details.
                tfMaintDetails.setText("");

                // Un-check the 'add another' checkbox.
                cbAddAnother.setSelected(false);
            }
            else {

                // Create the maintenance object
                MaintenanceWrapper mw = new MaintenanceWrapper(maintenance);
                mw.add();

                // Close the form only if the 'add another' is NOT selected
                fMaintNew.dispose();
            }
        });
    }
}



