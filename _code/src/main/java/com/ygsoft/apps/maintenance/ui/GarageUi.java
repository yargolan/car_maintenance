package com.ygsoft.apps.maintenance.ui;

import java.awt.*;
import javax.swing.*;

import com.ygsoft.apps.maintenance.Garage;
import com.ygsoft.apps.maintenance.GarageAlreadyExistsException;
import com.ygsoft.apps.maintenance.GarageWrapper;
import com.ygsoft.common.*;
import com.ygsoft.common.ui.*;
import com.ygsoft.apps.maintenance.hc.*;
import com.ygsoft.apps.maintenance.AppData;



public class GarageUi {

    private final AppData appData = AppData.getInstance();


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

            Garage garage = new Garage(readGarageName, readGarageLocation, readGaragePhone, readGarageContact);
            GarageWrapper gw = new GarageWrapper(garage);
            try {
                gw.add();
            }
            catch (GarageAlreadyExistsException ge) {
                System.out.println("ge = " + this.getClass().getName());
                if (ge.getClass().getName().equals(this.getClass().getName() + ".GarageAlreadyExistsException")) {
                    Messages.showMessage(Messages.MESSAGE_INF, ge.getMessage());
                    return;
                }
                else {
                    Messages.exitWithError("BOO");
                }
            }

            Messages.showMessage(Messages.MESSAGE_INF, HcUserMessages.M_GARAGE_ADD_OK.getText());
            fNewGarage.dispose();
        });


        fNewGarage.setVisible(true);
    }
}