package com.example.StudentApplication.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.StudentApplication.Dto.Student;
import com.example.StudentApplication.Repository.Student_Repo;
@RestController
public class Student_Controller {
	@Autowired
	Student_Repo student_Repo;
	@CrossOrigin(origins = "http://localhost:5173")
	@GetMapping("/project")

	String hello() {
		return "Welcome to project";
	}
	@PostMapping("/create")
	 ResponseEntity<Student> createStudent(@RequestBody Student student) {
		student=student_Repo.save(student);
		return new ResponseEntity<Student>(student,HttpStatus.CREATED);
	 }
	@PatchMapping("/update/{id}")
	public ResponseEntity<Student> updateStudent(
			@PathVariable int id,
			@RequestBody Student updatedStudent) {

		Optional<Student> optionalStudent = student_Repo.findById(id);

		if (!optionalStudent.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Student existingStudent = optionalStudent.get();

		if (updatedStudent.getFirstname() != null) {
			existingStudent.setFirstname(updatedStudent.getFirstname());
		}

		if (updatedStudent.getLastname() != null) {
			existingStudent.setLastname(updatedStudent.getLastname());
		}

		if (updatedStudent.getPhonenumber() != null) {
			existingStudent.setPhonenumber(updatedStudent.getPhonenumber());
		}

		if (updatedStudent.getBranch() != null) {
			existingStudent.setBranch(updatedStudent.getBranch());
		}

		if (updatedStudent.getCollege() != null) {
			existingStudent.setCollege(updatedStudent.getCollege());
		}

		if (updatedStudent.getLocation() != null) {
			existingStudent.setLocation(updatedStudent.getLocation());
		}

		if (updatedStudent.getFees() != 0) {  // because long default is 0
			existingStudent.setFees(updatedStudent.getFees());
		}

		student_Repo.save(existingStudent);

		return new ResponseEntity<>(existingStudent, HttpStatus.OK);
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
