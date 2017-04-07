package com.wumugulu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wumugulu.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
