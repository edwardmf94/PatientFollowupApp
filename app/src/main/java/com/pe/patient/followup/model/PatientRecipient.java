package com.pe.patient.followup.model;

public class PatientRecipient {
    private String Cif;
    private String Name;
    private String priceList;
    private String Iafas;

    public PatientRecipient(String cif, String name, String priceList, String iafas) {
        Cif = cif;
        Name = name;
        this.priceList = priceList;
        Iafas = iafas;
    }

    public String getCif() {
        return Cif;
    }

    public void setCif(String cif) {
        Cif = cif;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPriceList() {
        return priceList;
    }

    public void setPriceList(String priceList) {
        this.priceList = priceList;
    }

    public String getIafas() {
        return Iafas;
    }

    public void setIafas(String iafas) {
        Iafas = iafas;
    }
}
