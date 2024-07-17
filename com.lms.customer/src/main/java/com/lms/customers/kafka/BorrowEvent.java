package com.lms.customers.kafka;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowEvent {

	private String message;
	
	private long borrowId;
	
	private long bookId;
	
	private long customerId;
	
	private LocalDateTime borrowedDate;

	private LocalDateTime returnedDate;
	
}
