package com.example.MongoApp;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MongoApp.model.student;
import com.example.MongoApp.model.userRepo;

import jakarta.servlet.http.HttpServletResponse;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class addUser {
	
	@Autowired
	userRepo repo;
	
	/*@ApiIgnore
	@RequestMapping(value="/")
	public void redirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("/swagger-ui.html");
	}*/
	
	@GetMapping("/getalluser")
	public List<student> getAll(){
		if(repo.findAll().isEmpty())
			System.out.println("The student database is empty");
		return repo.findAll();
	}
		
	@PostMapping("/addUser")
	public ResponseEntity<student> create(@RequestBody student newJoinee) throws ServerException {
		student newStudent = repo.save(newJoinee);
		if (newStudent == null) {
	        throw new ServerException("Failed to create student");
	    } else {
	        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
	    }
	}
	
	
	@PutMapping("/updateUser")
	public student updateStudent(@RequestParam(required = true) String id, @RequestParam(required = false) Integer age,@RequestParam(required = false) String name,@RequestParam(required = false) String department,@RequestParam(required = false) String city)
	{
		student s = repo.findById(city).orElseThrow(()->new IllegalStateException());
		if(name != null)
			s.setName(name);
		System.out.println(age);
		if(age>0)
			s.setAge(age);
		if(department == null)
			s.setDepartment(department);
		if(city == null)
			s.setCity(city);
		
		repo.save(s);
		return s;
		
	}
}
