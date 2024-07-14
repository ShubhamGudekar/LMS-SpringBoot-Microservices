package com.lms.customers.kafka;

import com.lms.customers.entities.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEvent {

	private String message;

	private Customer customer;
}
