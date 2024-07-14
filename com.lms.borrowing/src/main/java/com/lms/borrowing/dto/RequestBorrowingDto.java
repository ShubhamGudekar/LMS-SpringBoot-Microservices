package com.lms.borrowing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBorrowingDto {

	private long id;

	private long bookId;

	private long customerId;

}
