package com.lms.borrowing.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "bookId", nullable = false)
	private Book book; // Reference to Book entity

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "customerId", nullable = false)
	private Customer customer; // Reference to Customer entity

	private LocalDateTime borrowedDate;

	private LocalDateTime returnedDate;

	public Borrowing(Book book, Customer customer, LocalDateTime borrowedDate) {
		super();
		this.book = book;
		this.customer = customer;
		this.borrowedDate = borrowedDate;
	}

}
