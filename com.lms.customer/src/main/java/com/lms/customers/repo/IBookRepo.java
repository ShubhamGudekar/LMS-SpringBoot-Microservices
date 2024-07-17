package com.lms.customers.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.customers.entities.Book;

@Repository
public interface IBookRepo extends JpaRepository<Book, Long> {

}
