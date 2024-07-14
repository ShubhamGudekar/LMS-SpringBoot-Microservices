package com.lms.borrowing.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.borrowing.entities.Book;

@Repository
public interface IBookRepo extends JpaRepository<Book, Long> {

}
