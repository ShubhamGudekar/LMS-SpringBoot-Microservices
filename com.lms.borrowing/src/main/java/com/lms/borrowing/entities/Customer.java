package com.lms.borrowing.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

	@Id
	private long id;

	private String firstname;

	private String lastname;

	private String emailId;

	private LocalDate birthdate;

	private LocalDateTime registeredOn;

	// One customer can have many borrowings
	@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	private List<Borrowing> borrowings;

}
