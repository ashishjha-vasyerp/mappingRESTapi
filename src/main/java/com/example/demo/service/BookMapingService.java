package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Book;

public interface BookMapingService {
	
	List<Book> getAllBooks();
	
	Optional<Book> getBookById(int id);
	
	Book saveBook(Book book);
	
	void deleteBookById(int id);

}
