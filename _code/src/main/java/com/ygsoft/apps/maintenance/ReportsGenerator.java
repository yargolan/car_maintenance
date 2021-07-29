package com.ygsoft.apps.maintenance;

import java.util.List;
import java.util.ArrayList;
import com.ygsoft.common.Messages;
import com.ygsoft.apps.maintenance.hc.HcUserMessages;


public class ReportsGenerator {

    public final static int REPORT_BY_MAINT_TYPE  = 1;
    public final static int REPORT_BY_GARAGE_NAME = 2;

    private final int reportIndex;



    public ReportsGenerator(int index){
        this.reportIndex = index;
    }



    public void generate(String filter) {

        switch (this.reportIndex) {
            case REPORT_BY_GARAGE_NAME:
                createReportByGarage(filter);
                break;

            case REPORT_BY_MAINT_TYPE:
                createReportByType(filter);
                break;

            default:
                Messages.showMessage(Messages.MESSAGE_ERR, "Invalid filter");
        }
    }



    private void createReportByGarage(String filter) {

        MaintenanceWrapper maintenanceWrapper = new MaintenanceWrapper();

        List<Maintenance> allMaintenances = maintenanceWrapper.getAllMaintenance();

        List<Maintenance> relevant = new ArrayList<>();

        for (Maintenance m : allMaintenances) {
            if (m.getGarageName().equals(filter)) {
                relevant.add(m);
            }
        }

        if (relevant.isEmpty()) {
            Messages.showMessage(Messages.MESSAGE_INF, HcUserMessages.M_NO_DATA_FOUND.getText());
        }
        else {
            for (Maintenance m : relevant) {
                m.toScreen();
            }
        }
    }



    private void createReportByType(String filter) {

        MaintenanceWrapper maintenanceWrapper = new MaintenanceWrapper();

        List<Maintenance> allMaintenances = maintenanceWrapper.getAllMaintenance();

        List<Maintenance> relevant = new ArrayList<>();

        for (Maintenance m : allMaintenances) {
            if (m.getMaintType().equals(filter)) {
                relevant.add(m);
            }
        }

        if (relevant.isEmpty()) {
            Messages.showMessage(Messages.MESSAGE_INF, HcUserMessages.M_NO_DATA_FOUND.getText());
        }
        else {
            for (Maintenance m : relevant) {
                m.toScreen();
            }
        }
    }
}
