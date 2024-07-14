package com.lms.borrowing.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.borrowing.entities.Borrowing;

public interface IBorrowingRepo extends JpaRepository<Borrowing, Long> {

	List<Borrowing> findByCustomerId(long id);
}
