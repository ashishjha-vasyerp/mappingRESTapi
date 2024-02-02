package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Stud;

public interface StudentMappingService {
	
	List<Stud> getAllStudents();
	
	Optional<Stud> getStudentById(int id);
	
	Stud saveStudent(Stud student);
	
	void deleteStudentById(int id);
}
