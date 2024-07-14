package com.lms.books.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.books.dto.BookDto;
import com.lms.books.entities.Book;
import com.lms.books.kafka.BookEvent;
import com.lms.books.kafka.BookProducer;
import com.lms.books.service.IBookService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

	IBookService bookService;
	BookProducer bookProducer;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Book addBook(@RequestBody BookDto bookDto) {
		Book book = bookService.addBook(bookDto);
		bookProducer.sendMessage(new BookEvent("BookAdded", book));
		return book;
	}

	@GetMapping("/{id}")
	public Book findBookById(@PathVariable long id) {
		return bookService.getBookDetails(id);
	}

	@GetMapping()
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@PutMapping("/{bookId}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Book updateBookDetails(@PathVariable long bookId, @RequestBody BookDto bookDto) {
		Book book = bookService.updateBook(bookId, bookDto);
		bookProducer.sendMessage(new BookEvent("BookUpdated", book));
		return book;
	}

	@DeleteMapping("/{id}")
	public Book removeBook(@PathVariable long id) {
		Book book = bookService.removeBook(id);
		bookProducer.sendMessage(new BookEvent("BookRemoved", book));
		return book;
	}

}
