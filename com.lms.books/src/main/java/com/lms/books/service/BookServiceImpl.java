package com.lms.books.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.books.dto.BookDto;
import com.lms.books.entities.Book;
import com.lms.books.repo.IBookRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookServiceImpl implements IBookService {

	@Autowired
	IBookRepo booksRepo;

	@Override
	public List<Book> getAllBooks() {
		return booksRepo.findAll();
	}

	@Override
	public Book getBookDetails(long id) {
		return booksRepo.findById(id).orElseThrow(() -> new RuntimeException("Book not found for id '" + id + "'"));
	}

	@Override
	public Book addBook(BookDto bookDto) {
		Book book = new Book(bookDto.getName(), bookDto.getDescription(), bookDto.getQuantity());
		return booksRepo.save(book);
	}

	@Override
	public Book removeBook(long id) {
		Book book = getBookDetails(id);
		booksRepo.delete(book);
		return book;
	}

	@Override
	public Book updateBook(long id, BookDto bookDto) {
		Book existingBook = getBookDetails(id);
		existingBook.setName(bookDto.getName());
		existingBook.setDescription(bookDto.getDescription());
		existingBook.setQuantity(bookDto.getQuantity());
		return booksRepo.save(existingBook);
	}

}
