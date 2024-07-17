package com.lms.customers.kafka;

import com.lms.customers.entities.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEvent {

	private String message;
	
	private Book book;
}
