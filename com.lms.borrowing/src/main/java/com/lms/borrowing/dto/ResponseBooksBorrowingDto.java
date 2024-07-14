package com.lms.borrowing.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBooksBorrowingDto {

	private long id;

	private BookDto bookDto;

	private LocalDateTime borrowedDate;

	private LocalDateTime returnedDate;
	
}
