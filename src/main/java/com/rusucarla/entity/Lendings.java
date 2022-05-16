package com.rusucarla.entity;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;


@Entity
@Table
public class Lendings {
    @Id
    @GeneratedValue
    @Column(nullable = false,updatable = false)
    private int id;
    @NotNull
    private Date return_date;
    @NotNull
    private int penalty;
    @ManyToOne
    @NotNull
    private Customer customerFK;
    @ManyToOne
    @NotNull
    private Book bookFK;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public Customer getCustomerFK() {
        return customerFK;
    }

    public void setCustomerFK(Customer customerFK) {
        this.customerFK = customerFK;
    }

    public Book getBookFK() {
        return bookFK;
    }

    public void setBookFK(Book bookFK) {
        this.bookFK = bookFK;
    }
}
