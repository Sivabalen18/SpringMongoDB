package com.example.MongoApp.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.MongoApp.model.student;

public interface StudentService {
	
	public List<student> getStudentData();
	public List<student> getStudentWithSpring(String course);
	public List<student> getStudentWithSpringOrMongoDB(List<String> courses);
	public List<student> getStudentWithMongoDBAndNotSpringBoot(String course1,String course2);
	
	public ResponseEntity<?> getStudentWithId(String id);
	public ResponseEntity<String> deleteStudent(String id);
	public ResponseEntity<?> addStudent(student user);
	public ResponseEntity<?> updateStudent(student user);
	 

}
