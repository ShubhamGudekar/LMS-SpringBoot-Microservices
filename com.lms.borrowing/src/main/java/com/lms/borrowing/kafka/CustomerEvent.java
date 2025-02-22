package com.lms.borrowing.kafka;

import com.lms.borrowing.entities.Customer;

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
