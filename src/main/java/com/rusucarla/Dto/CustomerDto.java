package com.rusucarla.Dto;


public class CustomerDto {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String cnp;
    private String address;

    public CustomerDto(int id, String name, String email, String phone, String cnp, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cnp = cnp;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
