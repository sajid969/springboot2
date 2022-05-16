package com.rusucarla.Dto;

import com.rusucarla.entity.Book;

import java.util.ArrayList;

public class SpecialCustomerDto {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String cnp;
    private String address;
    ArrayList<Book> books;

    public SpecialCustomerDto(int id, String name, String email, String phone, String cnp, String address, ArrayList<Book> books) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cnp = cnp;
        this.address = address;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
