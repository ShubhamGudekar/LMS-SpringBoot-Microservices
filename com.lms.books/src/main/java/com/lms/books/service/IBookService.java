package com.lms.books.service;

import java.util.List;

import com.lms.books.dto.BookDto;
import com.lms.books.entities.Book;

public interface IBookService {

	List<Book> getAllBooks();

	Book getBookDetails(long id);

	Book addBook(BookDto book);

	Book removeBook(long id);

	Book updateBook(long id, BookDto book);

}
