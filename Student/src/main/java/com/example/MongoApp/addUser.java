package com.example.MongoApp;

import java.rmi.ServerException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MongoApp.model.student;
import com.example.MongoApp.model.userRepo;

@RestController
public class addUser {
	
	@Autowired
	userRepo repo;
	
	/*@ApiIgnore
	@RequestMapping(value="/")
	public void redirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("/swagger-ui.html");
	}*/
	
	//Displaying all the records from the student database
	
	@GetMapping("/getalluser")
	public List<student> getAll(){
		if(repo.findAll().isEmpty())
			System.out.println("The student database is empty");
		return repo.findAll();
	}
	
	//Adding a student to the database
	
	@PostMapping("/addUser")
	public ResponseEntity<student> create(@RequestBody student newJoinee) throws ServerException {
		student newStudent = repo.save(newJoinee);
		if (newStudent == null) {
	        throw new ServerException("Failed to create student");
	    } else {
	        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
	    }
	}
	
	//Updating student information
	
	@PutMapping("/updateUser")
	public student updateStudent(@RequestBody student s) throws ServerException
	{
		student modifyStudent = repo.findById(s.getId()).orElse(null);
		
		modifyStudent.setAge(s.getAge());
		modifyStudent.setDepartment(s.getDepartment());
		modifyStudent.setCity(s.getCity());
		modifyStudent.setName(s.getName());
		repo.save(modifyStudent);
		return modifyStudent;	
	}
	
	//Searching Student by using pathvariable
	
	@GetMapping("/searchUserById/{id}")
	public student searchById(@PathVariable("id") String id) {
		return repo.findById(id).get();
	}

	//Deleting Student from the database
	
	@DeleteMapping("/deleteUserById")
	public void deleteUserById(@RequestParam("id") String id) {
		repo.deleteById(id);
		
	}
}
