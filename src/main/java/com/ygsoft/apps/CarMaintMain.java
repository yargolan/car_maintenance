package com.ygsoft.apps;

import java.sql.*;
import java.io.File;

import com.ygsoft.apps.hc.HcSql;
import com.ygsoft.apps.ui.CarMaintMainUi;



public class CarMaintMain {

    private final DataSingleton dataSingleton = DataSingleton.getInstance();
    private final File dbMaint   = dataSingleton.getDbMaint();
    private final File dbGarages = dataSingleton.getDbGarages();


    private CarMaintMain(){}



    public static void main(String[] args) {
        try {
            CarMaintMain carMaintMain = new CarMaintMain();
            carMaintMain.initApp();

            CarMaintMainUi carMaintMainUi = new CarMaintMainUi();
            carMaintMainUi.setAndShowUi();
        }
        catch (Throwable t) {
            Messages.exitWithError(t.getMessage(), true);
        }
    }



    private void initApp() throws CarMaintenanceInternalException {

        File dataFolder = dataSingleton.getDataDir();


        // Data folder
        if (!dataFolder.exists()) {
            if (!dataFolder.mkdirs()) {
                Messages.exitWithError("Cannot create the 'data' folder. Abort", true);
            }
        }


        // Databases - Garages.
        if (dbGarages.exists()) {
            System.out.println("DB garages exists.");
        }
        else {

            String sql = String.format("CREATE TABLE %s"
                            + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL)",
                    HcSql.TABLE_NAME_GARAGES.getText(),
                    HcSql.COLUMN_GARAGE_NAME.getText(),
                    HcSql.COLUMN_GARAGE_PHONE.getText(),
                    HcSql.COLUMN_GARAGE_CONTACT.getText(),
                    HcSql.COLUMN_GARAGE_LOCATION.getText()
            );

            try {

                Class.forName("org.sqlite.JDBC");

                Connection c = DriverManager.getConnection("jdbc:sqlite:" + dbGarages);

                Statement stmt = c.createStatement();

                stmt.executeUpdate(sql);

                stmt.close();

                c.close();
            }
            catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                throw new CarMaintenanceInternalException(e.getMessage());
            }
        }


        // Databases - Maintenance.
        if (dbMaint.exists()) {
            System.out.println("DB maintenance exists.");
        }
        else {

            String sql = String.format("CREATE TABLE %s"
                            + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL, "
                            + "%s TEXT NOT NULL)",
                    HcSql.TABLE_NAME_MAINT.getText(),
                    HcSql.COLUMN_MAINT_DATE.getText(),
                    HcSql.COLUMN_MAINT_TYPE.getText(),
                    HcSql.COLUMN_MAINT_GARAGE_INDEX.getText(),
                    HcSql.COLUMN_MAINT_DETAILS.getText()
            );

            try {
                Class.forName("org.sqlite.JDBC");

                Connection c = DriverManager.getConnection("jdbc:sqlite:" + dbMaint);

                Statement stmt = c.createStatement();

                stmt.executeUpdate(sql);

                stmt.close();

                c.close();
            }
            catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                throw new CarMaintenanceInternalException(e.getMessage());
            }
        }
    }
}















