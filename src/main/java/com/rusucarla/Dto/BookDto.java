package com.rusucarla.Dto;


public class BookDto {
    private int id;
    private String title;
    private String publisher;
    private float price;

    public BookDto(int id, String title, String publisher, float price) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
