package com.rusucarla.repository;

import com.rusucarla.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findCustomerByCnp(String cnp);
    Customer findCustomerById(int id);
    Customer findCustomerByName(String name);
}
