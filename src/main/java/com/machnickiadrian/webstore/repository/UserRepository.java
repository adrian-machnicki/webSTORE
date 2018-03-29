package com.machnickiadrian.webstore.repository;

import java.util.ArrayList;

import org.springframework.data.repository.Repository;

import com.machnickiadrian.webstore.entity.User;

public interface UserRepository extends Repository<User, Long> {

	User findByUsername(String username);
	
	User findById(Long id);
	
	User findByEmail(String email);
	
	ArrayList<User> findAll();

	void save(User user);
	
	void deleteById(Long id);

}