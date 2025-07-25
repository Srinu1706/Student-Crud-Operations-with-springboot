package com.example.StudentApplication.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudentApplication.Dto.Student;
import com.example.StudentApplication.Repository.Student_Repo;
@RestController
public class Student_Controller {
	@Autowired
	Student_Repo student_Repo;

	@GetMapping("/project")
	String hello() {
		return "Welcome to project";
	}
	@PostMapping("/create")
	 ResponseEntity<Student> createStudent(@RequestBody Student student) {
		student=student_Repo.save(student);
		return new ResponseEntity<Student>(student,HttpStatus.CREATED);
	 }
	@PutMapping("/update")
	ResponseEntity<Student>modifyStudent(@RequestBody Student student){
		student=student_Repo.save(student);
		return new ResponseEntity<Student>(student,HttpStatus.OK);
		
	}
	@GetMapping("/getstudentdetails")
	ResponseEntity<List<Student>> getstudentdetails( ){
	List<Student> students=student_Repo.findAll();
	return new ResponseEntity<>(students,HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	ResponseEntity<String> deleteStudentByid(@PathVariable int id){
		student_Repo.deleteById(id);
		  return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
	}
	@GetMapping("students/{branch}")
	ResponseEntity<List<Student>> getStudentByBranch(@PathVariable String branch){
		List<Student> students=student_Repo.findByBranch(branch);
		return new ResponseEntity<>(students,HttpStatus.OK);
	}
}
