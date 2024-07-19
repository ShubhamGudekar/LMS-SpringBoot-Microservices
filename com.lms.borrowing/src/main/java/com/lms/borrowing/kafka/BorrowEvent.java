package com.lms.borrowing.kafka;

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

	private String bookName;

	private long customerId;

	private String customerFirstname;

	private String customerLastname;

	private String customerEmaild;

	private LocalDateTime borrowedDate;

	private LocalDateTime returnedDate;
	
	public BorrowEvent(String message, long borrowId, long bookId, String bookName, long customerId,
			String customerFirstname, String customerLastname, String customerEmaild, LocalDateTime borrowedDate) {
		super();
		this.message = message;
		this.borrowId = borrowId;
		this.bookId = bookId;
		this.bookName = bookName;
		this.customerId = customerId;
		this.customerFirstname = customerFirstname;
		this.customerLastname = customerLastname;
		this.customerEmaild = customerEmaild;
		this.borrowedDate = borrowedDate;
	}

	public BorrowEvent(String message, long borrowId, LocalDateTime returnedDate) {
		super();
		this.message = message;
		this.borrowId = borrowId;
		this.returnedDate = returnedDate;
	}

}
