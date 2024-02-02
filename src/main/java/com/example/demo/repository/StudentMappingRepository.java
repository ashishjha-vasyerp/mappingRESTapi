package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Stud;



public interface StudentMappingRepository extends JpaRepository<Stud, Integer> {

}
