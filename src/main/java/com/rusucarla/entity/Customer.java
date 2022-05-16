package com.rusucarla.entity;



import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue
    @Column(nullable = false,updatable = false)
    private int id;
    private Date dateOpened;
    private String name;
    private String email;
    private String phone;
    private String cnp;
    private String address;
    @OneToMany(mappedBy = "customerFK")
    private Set<Lendings> lendings = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
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
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(dateOpened, customer.dateOpened) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(phone, customer.phone) &&
                Objects.equals(cnp, customer.cnp) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(lendings, customer.lendings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOpened, name, email, phone, cnp, address, lendings);
    }
}
