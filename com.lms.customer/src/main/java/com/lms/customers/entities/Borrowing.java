package com.lms.customers.entities;

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

	private LocalDateTime borrowedDate;

	private LocalDateTime returnedDate;

}
