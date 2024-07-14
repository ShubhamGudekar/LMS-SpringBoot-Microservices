package com.lms.customers.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

	private String firstname;

	private String lastname;

	@Email(message = "Please enter valid email id")
	private String emailId;

	@Past(message = "Please enter valid birthdate")
	private LocalDate birthdate;

}
