package com.lms.books.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.books.entities.Borrowing;

public interface IBorrowingRepo extends JpaRepository<Borrowing, Long> {

}
