package com.machnickiadrian.webstore.service;

import java.util.List;

import com.machnickiadrian.webstore.dto.UserDto;
import com.machnickiadrian.webstore.entity.User;

public interface UserService {
	
	void registerNewUser(UserDto userDto);
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	List<User> findAll();

	void save(User user);
	
	void deleteById(Long id);
	
	void setEnabledDisabled(Long userId);

	List<User> search(String phrase);

}