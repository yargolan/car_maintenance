package com.ygsoft.apps.ui;

import java.awt.*;
import javax.swing.*;

import com.ygsoft.apps.CarMaintenanceInternalException;
import com.ygsoft.apps.DataSingleton;
import com.ygsoft.apps.hc.*;
import com.ygsoft.apps.Messages;
import com.ygsoft.apps.SqlWrapper1;



public class GarageUi {

    private final DataSingleton dataSingleton = DataSingleton.getInstance();


    public GarageUi(){}


    public static void main(String[] args) {
        GarageUi ui = new GarageUi();
        ui.addNew();
    }


    public void addNew() {
        UiWrapper uiWrapper = new UiWrapper();
        JFrame fNewGarage = uiWrapper.generateFrame(
                UiWrapper.FRAME_SIZE_S,
                WindowConstants.DISPOSE_ON_CLOSE,
                HcFramesTitles.T_FRAME_NEW_GARAGE.getText()
        );

        JLabel lGarageName     = new JLabel(HcLabelsGarage.L_GARAGE_NAME.getText());
        JLabel lGaragePhone    = new JLabel(HcLabelsGarage.L_GARAGE_PHONE.getText());
        JLabel lGarageContact  = new JLabel(HcLabelsGarage.L_GARAGE_CONTACT.getText());
        JLabel lGarageLocation = new JLabel(HcLabelsGarage.L_GARAGE_LOCATION.getText());

        JTextField tfGarageName     = new JTextField();
        JTextField tfGaragePhone    = new JTextField();
        JTextField tfGarageContact  = new JTextField();
        JTextField tfGarageLocation = new JTextField();

        JButton bApprove = new JButton(HcGeneral.B_APPROVE.getText());

        // Locations
        lGarageName.setBounds    (220, 20, 100, 20);
        lGaragePhone.setBounds   (210, 100,100, 20);
        lGarageContact.setBounds (225, 60, 100, 20);
        lGarageLocation.setBounds(240, 140,100, 20);

        tfGarageName.setBounds    (80, 20, 120, 25);
        tfGaragePhone.setBounds   (80, 100,120, 25);
        tfGarageContact.setBounds (80, 60 ,120, 25);
        tfGarageLocation.setBounds(80, 140,120, 25);

        bApprove.setBounds(100, 190, 100, 40);

        Container container = fNewGarage.getContentPane();

        container.add(lGarageName);
        container.add(lGaragePhone);
        container.add(lGarageContact);
        container.add(lGarageLocation);

        container.add(tfGarageName);
        container.add(tfGaragePhone);
        container.add(tfGarageContact);
        container.add(tfGarageLocation);

        container.add(bApprove);

        bApprove.addActionListener(e->{

            // Read the form
            String readGarageName     = tfGarageName.getText();
            String readGaragePhone    = tfGaragePhone.getText();
            String readGarageContact  = tfGarageContact.getText();
            String readGarageLocation = tfGarageLocation.getText();


            // Validate
            if (readGarageName == null || readGarageName.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HcErrors.E_GARAGE_NAME.getText());
                return;
            }

            if (readGarageContact == null || readGarageContact.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HcErrors.E_GARAGE_CONTACT.getText());
                return;
            }

            if (readGaragePhone == null || readGaragePhone.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HcErrors.E_GARAGE_PHONE.getText());
                return;
            }

            if (readGarageLocation == null || readGarageLocation.isEmpty()) {
                Messages.showMessage(Messages.MESSAGE_ERR, HcErrors.E_GARAGE_LOCATION.getText());
                return;
            }


            String sqlCommand = String.format("INSERT INTO %s "
                    + "(%s, %s, %s, %s) "
                    + "VALUES ('%s', '%s', '%s', '%s') ",
                    HcSql.TABLE_NAME_GARAGES.getText(),
                    HcSql.COLUMN_GARAGE_NAME.getText(),
                    HcSql.COLUMN_GARAGE_PHONE.getText(),
                    HcSql.COLUMN_GARAGE_CONTACT.getText(),
                    HcSql.COLUMN_GARAGE_LOCATION.getText(),
                    readGarageName,
                    readGaragePhone,
                    readGarageContact,
                    readGarageLocation
            );


            SqlWrapper1 sqlWrapper1 = new SqlWrapper1(dataSingleton.getDbGarages());
            try {
                sqlWrapper1.runCommand(sqlCommand);
            }
            catch (CarMaintenanceInternalException ie) {
                ie.printStackTrace();
            }

            Messages.showMessage(Messages.MESSAGE_INF, HcUserMessages.M_GARAGE_ADD_OK.getText());
            fNewGarage.dispose();
        });


        fNewGarage.setVisible(true);
    }
}
