package com.ygsoft.apps;

import com.ygsoft.apps.hc.HcSql;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SqlWrapper {

    private final String connectionString;


    public SqlWrapper(File dbFile) {
        this.connectionString = "jdbc:sqlite:" + dbFile.getAbsolutePath();
    }



    public void runCommand(String sqlCommand) throws CarMaintenanceInternalException {

        if (! sqlCommand.endsWith(";")) {
            sqlCommand += ";";
        }

        try {
            Class.forName("org.sqlite.JDBC");

            Connection c = DriverManager.getConnection(this.connectionString);

            Statement stmt = c.createStatement();

            stmt.executeUpdate(sqlCommand);

            stmt.close();

            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            throw new CarMaintenanceInternalException(e.getMessage());
        }
    }



    public List<String> getGarageNames() throws CarMaintenanceInternalException {

        List<String> list = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");

            Connection c = DriverManager.getConnection(this.connectionString);

            c.setAutoCommit(false);

            Statement stmt = c.createStatement();

            String query = String.format("SELECT * FROM %s;", HcSql.TABLE_NAME_GARAGES.getText());

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString(HcSql.COLUMN_GARAGE_NAME.getText());
                list.add(name);
            }

            rs.close();

            stmt.close();

            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            throw new CarMaintenanceInternalException(e.getMessage());
        }

        return list;
    }
}

