package com.lms.customers.service;

import org.springframework.stereotype.Service;

import com.lms.customers.dto.CustomerDto;
import com.lms.customers.entities.Customer;
import com.lms.customers.repo.ICustomerRepo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

	ICustomerRepo customerRepo;

	@Override
	public Customer getCustomerDetailsById(long id) {
		return customerRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer Not Found for id '" + id + "'"));
	}

	@Override
	public Customer addCustomer(CustomerDto customerDto) {
		Customer customer = new Customer(0, customerDto.getFirstname(), customerDto.getLastname(),
				customerDto.getEmailId(), customerDto.getBirthdate(), null);
		return customerRepo.save(customer);
	}

	@Override
	public Customer removeCustomer(long id) {
		Customer customer = getCustomerDetailsById(id);
		customerRepo.delete(customer);
		return customer;
	}

	@Override
	public Customer editDetails(long id, CustomerDto customerDto) {
		Customer customer = getCustomerDetailsById(id);
		customer.setFirstname(customerDto.getFirstname());
		customer.setLastname(customerDto.getLastname());
		customer.setEmailId(customerDto.getEmailId());
		customer.setBirthdate(customer.getBirthdate());
		return customerRepo.save(customer);
	}

}
