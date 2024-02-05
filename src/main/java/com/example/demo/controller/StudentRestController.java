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
import com.example.demo.model.Stud;
import com.example.demo.service.BookMapingService;
import com.example.demo.service.StudentMappingService;

@RestController
@RequestMapping("/api/mapStudent")
public class StudentRestController {

    @Autowired
    private StudentMappingService studentMappingService;
    @Autowired
    private BookMapingService bookMapingService;

    @GetMapping("/all")
    public ResponseEntity<List<Stud>> getAllStudents() {
        return ResponseEntity.ok(studentMappingService.getAllStudents());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Stud> getStudentById(@PathVariable int id) {
        Optional<Stud> opStudent = studentMappingService.getStudentById(id);
        if (opStudent.isPresent()) {
            return ResponseEntity.ok(opStudent.get());
        } else {
            System.out.println("Student not found with ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Stud> saveStudent(@RequestBody Stud student) {
        // Check if the student already exists
        if (student.getId() != 0 && studentMappingService.getStudentById(student.getId()).isPresent()) {
            // Update existing student
            return ResponseEntity.ok(studentMappingService.saveStudent(student));
        } else {
            // Add new student
        	return ResponseEntity.ok(studentMappingService.saveStudent(student));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
    	Optional<Stud> student = studentMappingService.getStudentById(id);
    	
    	if(student.isPresent())
    	{
    		studentMappingService.deleteStudentById(id);
    		return ResponseEntity.ok("Success");
    	}
    	else
    	{
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No student found with id : " +id);
    	}
    }
    
    @PostMapping("/assign/{studentId}/{bookId}")
    public ResponseEntity<String> assignBookToStudent(@PathVariable int studentId, @PathVariable int bookId)
    {
    	Optional<Stud> student = studentMappingService.getStudentById(studentId);
    	Optional<Book> book = bookMapingService.getBookById(bookId);
    	
    	Stud stud = student.get();
    	
    	if (student.isPresent()  && book.isPresent())
    	{
    		
    		if (stud.getBook()!=null )
    		{
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student already has a book assigned");
    		}
    		student.get().setBook(book.get());
    		studentMappingService.saveStudent(student.get());
    		return ResponseEntity.ok("Book assigned to student suucessfully");
    	}
    	else
    	{
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found ");
    	}
    }
}
