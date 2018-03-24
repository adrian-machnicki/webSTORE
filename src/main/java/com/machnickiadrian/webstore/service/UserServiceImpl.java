package com.machnickiadrian.webstore.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.machnickiadrian.webstore.dto.UserDto;
import com.machnickiadrian.webstore.entity.Role;
import com.machnickiadrian.webstore.entity.User;
import com.machnickiadrian.webstore.exception.EmailExistsException;
import com.machnickiadrian.webstore.exception.UsernameExistsException;
import com.machnickiadrian.webstore.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	@Override
	public User registerNewUser(UserDto userDto) throws EmailExistsException, UsernameExistsException {

		if (emailExists(userDto.getEmail())) {
			throw new EmailExistsException();
		}
		if (usernameExists(userDto.getUsername())) {
			throw new UsernameExistsException();
		}

		User user = createUserFromDto(userDto);
		return repository.save(user);
	}

	private boolean emailExists(String email) {
		User user = repository.findByEmail(email);

		if (user != null)
			return true;
		else
			return false;
	}
	
	private boolean usernameExists(String username) {
		User user = repository.findByUsername(username);
		
		if (user != null)
			return true;
		else
			return false;
	}

	private User createUserFromDto(UserDto userDto) {
		
		Role role = new Role(userDto.getUsername(), "ROLE_USER");

		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(encoder.encode(userDto.getPassword()));
		user.setEmail(userDto.getEmail());
		user.setRoles(Arrays.asList(role));
		user.setEnabled(1);

		return user;
	}

}
