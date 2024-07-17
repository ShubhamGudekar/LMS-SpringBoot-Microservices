package com.lms.customers.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.customers.entities.Borrowing;

public interface IBorrowingRepo extends JpaRepository<Borrowing, Long> {

	List<Borrowing> findByCustomerId(long id);
}
