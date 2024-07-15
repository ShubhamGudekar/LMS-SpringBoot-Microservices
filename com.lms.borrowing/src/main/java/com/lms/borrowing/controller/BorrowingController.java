package com.lms.borrowing.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.borrowing.dto.RequestBorrowingDto;
import com.lms.borrowing.dto.ResponseBooksBorrowingDto;
import com.lms.borrowing.dto.ResponseBorrowingDto;
import com.lms.borrowing.service.IBorrowingService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/borrowings")
@AllArgsConstructor
public class BorrowingController {

	IBorrowingService borrowingService;

	@PostMapping("/customers/{customerId}/books/{bookId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseBorrowingDto addBorrowing(@PathVariable long customerId, @PathVariable long bookId) {
		RequestBorrowingDto borrowingDto = new RequestBorrowingDto(0, bookId, customerId);
		ResponseBorrowingDto responseBorrowingDto = borrowingService.addBorrowing(borrowingDto);
		return responseBorrowingDto;
	}

	@PutMapping("/{borrowingId}")
	public ResponseBorrowingDto closeBorrowing(@PathVariable long borrowingId) {
		ResponseBorrowingDto responseBorrowingDto = borrowingService.closeBorrowing(borrowingId);
		return responseBorrowingDto;
	}

	@GetMapping("/customers/{customerId}")
	public List<ResponseBooksBorrowingDto> getBorrowingDetailsByCustomerId(@PathVariable long customerId) {
		return borrowingService.getAllBorrowingByCustomerId(customerId);
	}

	@GetMapping("{id}")
	public ResponseBorrowingDto getBorrowingDetailsById(@PathVariable long id) {
		return borrowingService.getBorrowingDetailsById(id);
	}

}
