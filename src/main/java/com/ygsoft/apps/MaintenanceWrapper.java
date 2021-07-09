package com.ygsoft.apps;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import com.ygsoft.apps.hc.HcSql;



public class MaintenanceWrapper {

    private final DataSingleton dataSingleton = DataSingleton.getInstance();


    public MaintenanceWrapper() {}



    public void add(Maintenance maintenance) throws CarMaintenanceInternalException {

        String connectionString = "jdbc:sqlite:" + dataSingleton.getDbMaint().getAbsolutePath();

        String sql = String.format(
                "INSERT INTO %s (%s, %s, %s, %s) "
                + "VALUES ('%s', '%s', '%s', '%s');",
                HcSql.TABLE_NAME_GARAGES,
                HcSql.COLUMN_MAINT_DATE.getText(),
                HcSql.COLUMN_MAINT_TYPE.getText(),
                HcSql.COLUMN_MAINT_GARAGE_INDEX.getText(),
                HcSql.COLUMN_MAINT_DETAILS.getText(),
                maintenance.getDate(),
                maintenance.getMaintType(),
                maintenance.getGarageName(),
                maintenance.getMaintDetails()
        );

        try {

            Class.forName("org.sqlite.JDBC");

            Connection connection = DriverManager.getConnection(connectionString);

            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);

            statement.close();

            connection.commit();

            connection.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            throw new CarMaintenanceInternalException(e.getMessage());
        }
    }
}

