package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Stud;
import com.example.demo.repository.StudentMappingRepository;

@Service
public class StudentMappingServiceImpl implements StudentMappingService {

	@Autowired
	private StudentMappingRepository studentMappingRepository;

	@Override
	public List<Stud> getAllStudents() {
		// TODO Auto-generated method stub
		List<Stud> students = studentMappingRepository.findAll();
		return students;
	}

	@Override
	public Optional<Stud> getStudentById(int id) {
		return studentMappingRepository.findById(id);
	}

	@Override
	public Stud saveStudent(Stud student) {
		// TODO Auto-generated method stub
		return studentMappingRepository.save(student);
	}

	@Override
	public void deleteStudentById(int id) {
		// TODO Auto-generated method stub
		studentMappingRepository.deleteById(id);
	}

}
