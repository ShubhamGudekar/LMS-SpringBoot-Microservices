package com.lms.borrowing.entities;

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
public class Book {

	@Id
	private long id;

	private String name;

	private String description;

	private int quantity;

	// One book can be borrowed many times
	@JsonIgnore
	@JsonManagedReference
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<Borrowing> borrowings;
}
