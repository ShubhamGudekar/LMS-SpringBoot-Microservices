package com.lms.borrowing.dto;

import java.time.LocalDateTime;

import com.lms.borrowing.entities.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBorrowingDto {

	private long id;

	private BookDto bookDto;

	private Customer customer;

	private LocalDateTime borrowedDate;

	private LocalDateTime returnedDate;

	public ResponseBorrowingDto(long id, BookDto bookDto, Customer customer, LocalDateTime borrowedDate) {
		super();
		this.id = id;
		this.bookDto = bookDto;
		this.customer = customer;
		this.borrowedDate = borrowedDate;
	}

}
