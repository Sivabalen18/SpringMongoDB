package com.example.MongoApp.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface userRepo extends MongoRepository<student,String> {
	

}
