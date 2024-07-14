package com.lms.books.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.books.entities.Book;

@Repository
public interface IBookRepo extends JpaRepository<Book, Long> {

}
