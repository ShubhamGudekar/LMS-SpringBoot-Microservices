package com.lms.borrowing.kafka;

import com.lms.borrowing.entities.Book;

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
