package com.lms.books.kafka;

import com.lms.books.entities.Book;

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
