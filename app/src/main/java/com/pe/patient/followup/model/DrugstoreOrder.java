package com.pe.patient.followup.model;

public class DrugstoreOrder {
    private String details;
    private String dateStart;
    private String datePending;
    private String dateProgress;

    public DrugstoreOrder(String details, String dateStart, String datePending, String dateProgress) {
        this.details = details;
        this.dateStart = dateStart;
        this.datePending = datePending;
        this.dateProgress = dateProgress;
    }

    public String getDetails() {
        return details;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDatePending() {
        return datePending;
    }

    public String getDateProgress() {
        return dateProgress;
    }
}
