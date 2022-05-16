package com.rusucarla.repository;


import com.rusucarla.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface LendingsRepository extends JpaRepository<Lendings,Integer> {

    List<Lendings> findAllByBookFK(Book book);
    List<Lendings> findAllByCustomerFK(Customer customer);

}



