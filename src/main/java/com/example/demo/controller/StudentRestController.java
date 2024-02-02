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

import com.example.demo.model.Stud;
import com.example.demo.service.StudentMappingService;

@RestController
@RequestMapping("/api/mapStudent")
public class StudentRestController {
	  
		@Autowired
		private StudentMappingService studentMappingService;
		
		@GetMapping("/all")
		public ResponseEntity<List<Stud>> getAllStudents(){
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
		
		@PostMapping("/add")
		public ResponseEntity<Stud> addStudent(@RequestBody Stud stud)
		{
			return ResponseEntity.ok(studentMappingService.saveStudent(stud));
		}
		
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<String> deleteBook(@PathVariable int id)
		{
			studentMappingService.deleteStudentById(id);
			return ResponseEntity.ok("success");
		}
		
}
