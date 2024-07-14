package com.lms.customers.service;

import com.lms.customers.dto.CustomerDto;
import com.lms.customers.entities.Customer;

public interface ICustomerService {

	Customer getCustomerDetailsById(long id);

	Customer addCustomer(CustomerDto customerDto);

	Customer removeCustomer(long id);

	Customer editDetails(long id,CustomerDto customerDto);

}
