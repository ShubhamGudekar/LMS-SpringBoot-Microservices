package com.lms.borrowing.service;

import java.util.List;

import com.lms.borrowing.dto.RequestBorrowingDto;
import com.lms.borrowing.dto.ResponseBooksBorrowingDto;
import com.lms.borrowing.dto.ResponseBorrowingDto;

public interface IBorrowingService {

	ResponseBorrowingDto addBorrowing(RequestBorrowingDto borrwingDto);

	ResponseBorrowingDto closeBorrowing(long borrowingId);

	List<ResponseBooksBorrowingDto> getAllBorrowingByCustomerId(long customerId);

	ResponseBorrowingDto getBorrowingDetailsById(long id);
}
