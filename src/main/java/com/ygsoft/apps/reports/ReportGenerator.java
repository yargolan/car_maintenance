package com.ygsoft.apps.reports;

import com.ygsoft.apps.*;
import com.ygsoft.apps.hc.HcSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {

    private final DataSingleton dataSingleton = DataSingleton.getInstance();



    public ReportGenerator(){}



    public void generate_by_dates (String date_start, String date_end) {
        System.out.println("date_end   = " + date_end);
        System.out.println("date_start = " + date_start);
    }



    public void generate_by_garage_name(String name) {

        List<Maintenance> list = new ArrayList<>();

        String sqlQuery = String.format(
                "SELECT * FROM %s WHERE %s=='%s';",
                HcSql.TABLE_NAME_MAINT.getText(),
                HcSql.COLUMN_GARAGE_NAME.getText(),
                name
        );

        String dbFile = dataSingleton.getDbMaint().getAbsolutePath();

        String connectionString = "jdbc:sqlite:" + dbFile;

        try {

            Class.forName("org.sqlite.JDBC");

            Connection c = DriverManager.getConnection(connectionString);

            c.setAutoCommit(false);

            Statement stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery(sqlQuery);

            Maintenance m;

            while (rs.next()) {
                String gName   = rs.getString(HcSql.COLUMN_GARAGE_NAME.getText());
                String mType   = rs.getString(HcSql.COLUMN_MAINT_TYPE.getText());
                String mDate   = rs.getString(HcSql.COLUMN_MAINT_DATE.getText());
                String details = rs.getString(HcSql.COLUMN_MAINT_DETAILS.getText());
                m = new Maintenance(mDate, gName, mType, details);
                list.add(m);
            }

            rs.close();

            stmt.close();

            c.close();
        }
        catch ( Exception e ) {
            Messages.showMessage(Messages.MESSAGE_ERR, "Cannot get data for garage - " + name);
            return;
        }


        // Generate the report.
        for (Maintenance m : list) {
            System.out.println(m.getGarageName());
        }
    }



    private void generate_by_type(String type) {

        SqlWrapper1 sqlWrapper1 = new SqlWrapper1(dataSingleton.getDbMaint());
        List<String> lines = null;
        try {
            lines = sqlWrapper1.getMaintByType(type);
        }
        catch (CarMaintenanceInternalException e) {
            String message = String.format("Cannot get the list of maintenances per '%s': " + e.getMessage(), type);
            Messages.exitWithError(message, true);
        }


        if (lines == null) {
            System.out.printf("Nothing was found for '%s'.%n", type);
            return;
        }

        for (String s : lines) {
            System.out.println("s = " + s);
        }
    }


    public static void main(String[] args) {
        new ReportGenerator().generate_by_garage_name("מוסך מרעי");
    }
}














