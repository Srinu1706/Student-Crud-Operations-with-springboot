package com.example.StudentApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.StudentApplication.Dto.Student;

public interface Student_Repo extends JpaRepository<Student, Integer> {
	List<Student> findByBranch(String branch);//abstarct methode
}
