package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.service.BookMapingService;


@RestController
@RequestMapping("/api/book")
public class BookRestController {

	@Autowired
	private BookMapingService bookMapingService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Book>> getAllBooks()
	{
		return ResponseEntity.ok(bookMapingService.getAllBooks());
		
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Book> getBooktById(@PathVariable int id) {

		Optional<Book> opBook = bookMapingService.getBookById(id);
		if (opBook.isPresent()) {
			return ResponseEntity.ok(opBook.get());
		} else {
			System.out.println("Student not found with ID: " + id);
			return ResponseEntity.notFound().build();
		}

	}
	@PostMapping("/add")
	public ResponseEntity<Book> addBook(@RequestBody Book book)
	{
		
		return ResponseEntity.ok(bookMapingService.saveBook(book));
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable int id)
	{
		bookMapingService.deleteBookById(id);
		return ResponseEntity.ok("success");
	}
	
}
