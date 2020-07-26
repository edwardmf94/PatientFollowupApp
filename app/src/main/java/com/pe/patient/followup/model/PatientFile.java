package com.pe.patient.followup.model;

public class PatientFile {
    private String Department;
    private String Drugstore;
    private String primaryDiagnosis;
    private Integer id;

    public PatientFile(Integer id,String department, String drugstore, String primaryDiagnosis) {
        this.id = id;
        Department = department;
        Drugstore = drugstore;
        this.primaryDiagnosis = primaryDiagnosis;
    }
    public Integer getId() {
        return id;
    }
    public String getDepartment() {
        return Department;
    }

    public String getDrugstore() {
        return Drugstore;
    }

    public String getPrimaryDiagnosis() {
        return primaryDiagnosis;
    }
}
