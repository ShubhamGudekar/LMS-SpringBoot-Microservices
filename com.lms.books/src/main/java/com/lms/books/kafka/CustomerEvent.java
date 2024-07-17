package com.lms.books.kafka;

import com.lms.books.entities.Customer;

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
