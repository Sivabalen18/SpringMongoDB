package com.example.MongoApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.MongoApp.model.student;
import com.example.MongoApp.model.UserRepo;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public List<student> getStudentData(){
		return repo.findAll();
	}
	
	@Override
	public List<student> getStudentWithSpring(String course){
		return repo.findByCoursesIgnoreCase(course);
	}
	
	@Override
	public List<student> getStudentWithSpringOrMongoDB(List<String> courses){
		return repo.findByCoursesInIgnoreCase(courses);
	}
	
	@Override
	public List<student> getStudentWithMongoDBAndNotSpringBoot(String course1,String course2){
		Query query = new Query();
		query.addCriteria(Criteria.where("courses").in(course1).norOperator(Criteria.where("courses").in(course2)));
		return mongoTemplate.find(query,student.class);
	}

	@Override
	public ResponseEntity<?> getStudentWithId(String id) {
		if(repo.existsById(id))
			return new ResponseEntity<>(repo.findById(id).get(),HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<>("Student does not exist",HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<String> deleteStudent(String id) {
		try {
			if(repo.existsById(id)) {
				repo.delete(repo.findById(id).get());
				return new ResponseEntity<String>("User deleted successfully",HttpStatus.ACCEPTED);
			}
			else
				return new ResponseEntity<String>("User not found",HttpStatus.ACCEPTED);			
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
			return new ResponseEntity<String>("The input parameter is null",HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public ResponseEntity<?> addStudent(student user) {
		if(repo.existsById(user.getId())) 
			return new ResponseEntity<>("User already exists",HttpStatus.ACCEPTED);
		else {
			repo.save(user);
			return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
		}
	}

	@Override
	public ResponseEntity<?> updateStudent(student user) {
		try {
			if(repo.existsById(user.getId())) {
				repo.save(user);
				return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
			}
			else {
				return new ResponseEntity<>("Student does not exist with a given Id",HttpStatus.BAD_REQUEST);
			}
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
			return new ResponseEntity<String>("The input parameter is null",HttpStatus.BAD_REQUEST);
		}
	}
	

}
