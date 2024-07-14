package com.lms.customers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.customers.dto.CustomerDto;
import com.lms.customers.entities.Customer;
import com.lms.customers.kafka.CustomerEvent;
import com.lms.customers.kafka.CustomerProducer;
import com.lms.customers.service.ICustomerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/customers")
@AllArgsConstructor
public class CustomerController {

	ICustomerService customerService;
	CustomerProducer customerProducer;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Customer addBook(@RequestBody CustomerDto CustomerDto) {
		Customer customer = customerService.addCustomer(CustomerDto);
		customerProducer.sendMessage(new CustomerEvent("CustomerAdded", customer));
		return customer;
	}

	@GetMapping("/{id}")
	public Customer findCustomerById(@PathVariable long id) {
		return customerService.getCustomerDetailsById(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Customer updateBookDetails(@PathVariable long id, @RequestBody CustomerDto customerDto) {
		Customer customer = customerService.editDetails(id, customerDto);
		customerProducer.sendMessage(new CustomerEvent("CustomerUpdated", customer));
		return customer;
	}

	@DeleteMapping("/{id}")
	public Customer reomveCustomer(@PathVariable long id) {
		Customer customer = customerService.removeCustomer(id);
		customerProducer.sendMessage(new CustomerEvent("CustomerRemoved", customer));
		return customer;
	}

}
