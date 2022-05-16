package com.rusucarla.repository;

import com.rusucarla.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findAllByTitle(String title);
    Book findBookById(int id);
}
