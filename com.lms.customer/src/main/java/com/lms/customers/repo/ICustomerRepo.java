package com.lms.customers.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.customers.entities.Customer;

public interface ICustomerRepo extends JpaRepository<Customer, Long> {

}
