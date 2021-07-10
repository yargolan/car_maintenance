package com.ygsoft.apps.ui;

import com.ygsoft.apps.*;
import com.ygsoft.apps.hc.HcFramesTitles;
import com.ygsoft.apps.hc.HcGeneral;
import com.ygsoft.apps.hc.HcLabelsGarage;
import com.ygsoft.apps.hc.HcSql;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReportsUi {

    private final UiWrapper uiWrapper = new UiWrapper();



    public ReportsUi(){}


    public void generateReportPerGarage() {

        DataSingleton dataSingleton = DataSingleton.getInstance();

        JFrame f = uiWrapper.generateFrame(
                UiWrapper.FRAME_SIZE_XS,
                WindowConstants.DISPOSE_ON_CLOSE,
                HcFramesTitles.T_FRAME_REPORT_PER_GARAGE.getText()
        );

        JLabel lGarageName = new JLabel(HcLabelsGarage.L_GARAGE_NAME.getText());
        JComboBox<String> ddGarageName = new JComboBox<>();
        JButton bApprove = new JButton(HcGeneral.B_APPROVE.getText());


        bApprove.setBounds    (80, 70, 100, 40);
        lGarageName.setBounds (170,20, 100, 25);
        ddGarageName.setBounds(35, 20, 130, 25);

        f.getContentPane().add(bApprove);
        f.getContentPane().add(lGarageName);
        f.getContentPane().add(ddGarageName);

        SqlWrapper1 sqlWrapper1 = new SqlWrapper1(dataSingleton.getDbGarages());
        List<String> list;
        try {
            list = sqlWrapper1.getGarageNames();
        }
        catch (CarMaintenanceInternalException e) {
            list = new ArrayList<>();
        }

        for (String g : list) {
            ddGarageName.addItem(g);
        }
        ddGarageName.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


        bApprove.addActionListener(e->{

            // Get the chosen garage name.
            String garageName = (String)ddGarageName.getSelectedItem();

            String sqlQuery = String.format(
                    "SELECT * FROM %s where %s=='%s'",
                    HcSql.TABLE_NAME_GARAGES.getText(),
                    HcSql.COLUMN_GARAGE_NAME.getText(),
                    garageName

            );

            ResultSet rs = sqlWrapper1.runQuery(sqlQuery);

            if (rs != null) {
                try {
                    while (rs.next()) {
                        String name = rs.getString(HcSql.COLUMN_GARAGE_NAME.getText());
                        System.out.println("name = " + name);
                    }
                    System.out.println("rs = " + rs);
                }
                catch (SQLException s) {
                    s.printStackTrace();
                }
            }
        });


        f.setVisible(true);
    }


    public static void main(String[] args) {
        ReportsUi r = new ReportsUi();
        r.generateReportPerGarage();
    }
}
