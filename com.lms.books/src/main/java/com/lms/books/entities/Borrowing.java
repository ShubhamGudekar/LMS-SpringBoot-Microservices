package com.lms.books.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Borrowing {

	@Id
	private long id;

	private long bookId;

	private long customerId;

	private String customerFirstname;

	private String customerLastname;

	private String customerEmaild;

	private LocalDateTime borrowedDate;

	private LocalDateTime returnedDate;

	public Borrowing(long id, long bookId, long customerId, String customerFirstname, String customerLastname,
			String customerEmaild, LocalDateTime borrowedDate) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.customerId = customerId;
		this.customerFirstname = customerFirstname;
		this.customerLastname = customerLastname;
		this.customerEmaild = customerEmaild;
		this.borrowedDate = borrowedDate;
	}

	public Borrowing(long id, LocalDateTime returnedDate) {
		super();
		this.id = id;
		this.returnedDate = returnedDate;
	}

}
