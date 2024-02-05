package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookMapingService.getAllBooks());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Optional<Book> opBook = bookMapingService.getBookById(id);
        if (opBook.isPresent()) {
            return ResponseEntity.ok(opBook.get());
        } else {
            System.out.println("Book not found with ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        // Check if the book already exists
        if (book.getId() != 0 && bookMapingService.getBookById(book.getId()).isPresent()) {
            // Update existing book
            return ResponseEntity.ok(bookMapingService.saveBook(book));
        } else {
            // Add new book
        	return ResponseEntity.ok(bookMapingService.saveBook(book));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
    	
    	Optional<Book> book = bookMapingService.getBookById(id);
    	
    	if( book.isPresent())
    	{
    		bookMapingService.deleteBookById(id);
    		return ResponseEntity.ok("Success");
    	}
    	else
    	{
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No book found with id : " + id);
    	}
	

    }
}
