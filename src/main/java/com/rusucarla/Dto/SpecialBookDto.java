package com.rusucarla.Dto;

import com.rusucarla.entity.Customer;

import java.util.ArrayList;

public class SpecialBookDto {
    private int id;
    private String title;
    private String publisher;
    private float price;
    ArrayList<Customer> customers;

    public SpecialBookDto(int id, String title, String publisher, float price, ArrayList<Customer> customers) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.price = price;
        this.customers = customers;
    }
}
