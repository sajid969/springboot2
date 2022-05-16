package com.rusucarla.entity;



import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table
public class Book {
    @Id
    @GeneratedValue
    @Column(nullable = false,updatable = false)
    private int id;
    private String title;
    private String publisher;
    private float price;
    @OneToMany(mappedBy = "bookFK")
    private Set<Lendings> lendings = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<Lendings> getLendings() {
        return lendings;
    }

    public void setLendings(Set<Lendings> lendings) {
        this.lendings = lendings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                Float.compare(book.price, price) == 0 &&
                Objects.equals(title, book.title) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(lendings, book.lendings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, publisher, price, lendings);
    }
}
