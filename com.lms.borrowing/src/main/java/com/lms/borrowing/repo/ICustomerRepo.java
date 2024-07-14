package com.lms.borrowing.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.borrowing.entities.Customer;

public interface ICustomerRepo extends JpaRepository<Customer, Long> {

}
