package com.pe.patient.followup.model;

public class Patient {
    private String dni;
    private String namem;
    private String phone;
    private String birthDate;
    private String gender;
    private String department;
    private String province;
    private String district;
    private String address;
    private String comment;

    public String getDni() {
        return dni;
    }

    public String getNamem() {
        return namem;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public String getProvince() {
        return province;
    }

    public String getDistrict() {
        return district;
    }

    public String getAddress() {
        return address;
    }

    public String getComment() {
        return comment;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNamem(String namem) {
        this.namem = namem;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
