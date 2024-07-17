package com.lms.books.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.books.entities.Customer;

public interface ICustomerRepo extends JpaRepository<Customer, Long> {

}
