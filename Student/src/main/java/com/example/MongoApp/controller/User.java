package com.example.MongoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.MongoApp.Service.StudentService;
import com.example.MongoApp.model.student;

@RestController
public class User {

	
	@Autowired
	StudentService studentservice;

	//Displaying all the records from the student database
	
	@GetMapping("/getalluser")
	public List<student> getAll(){
		return studentservice.getStudentData();
	}
	
	//Adding a student to the database
	
	@PostMapping("/addUser")
	public ResponseEntity<?> create(@RequestBody student newJoinee){
		return studentservice.addStudent(newJoinee);
	}
	
	//Updating student information
	
	@PutMapping("/updateUser")
	public ResponseEntity<?> updateStudent(@RequestBody student modifyStudent){
		return studentservice.updateStudent(modifyStudent);
	}
	
	//Searching Student by using pathvariable
	
	@GetMapping("/searchUserById/{id}")
	public ResponseEntity<?> searchById(@PathVariable("id") String id) {
		return studentservice.getStudentWithId(id);
	}

	//Deleting Student from the database
	
	@DeleteMapping("/deleteUserById")
	public ResponseEntity<String> deleteUserById(@RequestParam("id") String id) {
		return studentservice.deleteStudent(id);
	}
	
	@GetMapping("/studentWithMongo")
	public List<student> studentWithSpring(@RequestParam("course") String course){
		return studentservice.getStudentWithSpring(course);
	}
	
	@GetMapping("/studentWithMongoOrSpring")
	public List<student> studentwithmongoorspring(@RequestBody List<String> courses){
		return studentservice.getStudentWithSpringOrMongoDB(courses);
	}
	
	@GetMapping("/studentWithMongoAndNotSpring")
	public List<student> studentWithMongoAndNotSpring(@RequestParam("course1") String course1,@RequestParam("course2") String course2){
		return studentservice.getStudentWithMongoDBAndNotSpringBoot(course1,course2);
	}	
}
	