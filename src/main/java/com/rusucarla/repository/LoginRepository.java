package com.rusucarla.repository;

import com.rusucarla.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;




public interface LoginRepository extends JpaRepository<Login,Integer> {

    Login findByUsername(String username);

}
