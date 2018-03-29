package com.machnickiadrian.webstore.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.machnickiadrian.webstore.dto.UserDto;
import com.machnickiadrian.webstore.entity.Order;
import com.machnickiadrian.webstore.entity.Role;
import com.machnickiadrian.webstore.entity.User;
import com.machnickiadrian.webstore.exception.EmailExistsException;
import com.machnickiadrian.webstore.exception.UsernameExistsException;
import com.machnickiadrian.webstore.repository.RoleRepository;
import com.machnickiadrian.webstore.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private static final int USER_ENABLED = 1;
	private static final int USER_DISABLED = 0;
	private static final String ADMIN = "ROLE_ADMIN";

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	@Override
	public void registerNewUser(UserDto userDto) {

		if (emailExists(userDto.getEmail())) {
			throw new EmailExistsException();
		}
		if (usernameExists(userDto.getUsername())) {
			throw new UsernameExistsException();
		}

		User user = createUserFromDto(userDto);
		userRepository.save(user);
	}

	@PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.username == principal.username")
	@Transactional(readOnly = true)
	@Override
	public User findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}
	
	@PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.username == principal.username")
	@Transactional(readOnly = true)
	@Override
	public User findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

	@RolesAllowed(ADMIN)
	@Transactional(readOnly = true)
	@Override
	public List<User> findAll() {

		return userRepository.findAll();
	}

	@Transactional
	@Override
	public void save(User user) {
		userRepository.save(user);
	}
	
	@RolesAllowed(ADMIN)
	@Transactional
	@Override
	public void deleteById(Long id) {
		User user = userRepository.findById(id);
		List<Role> userRoles = roleRepository.findByUsername(user.getUsername());
		
		for (Role role : userRoles)
			roleRepository.delete(role);
		
		for (Order order : user.getOrders())
			order.setUser(null);
		
		// without this line org.hsqldb.HsqlException is thrown
		// integrity constraint violation
		// NOT NULL check constraint
		// but users roles are removed indeed
		// TODO
		roleRepository.count();
		
		userRepository.deleteById(id);
	}
	
	@Secured(ADMIN)
	@Transactional
	@Override
	public void setEnabledDisabled(Long userId) {
		User user = userRepository.findById(userId);
		if(user.getEnabled() == USER_DISABLED)
			user.setEnabled(USER_ENABLED);
		else
			user.setEnabled(USER_DISABLED);
		
		userRepository.save(user);
	}
	
	@RolesAllowed(ADMIN)
	@Transactional(readOnly = true)
	@Override
	public List<User> search(String phrase) {
		phrase = phrase.trim();
		String[] keywords = phrase.split(" ");
		List<User> allUsers = userRepository.findAll();
		List<User> foundUsers = new ArrayList<>();
		StringJoiner userData;
		
		for (User user : allUsers) {
			userData = new StringJoiner(" ");
			userData.add(String.valueOf(user.getId()));
			userData.add(user.getFirstName());
			userData.add(user.getLastName());
			userData.add(user.getUsername());
			userData.add(user.getUserDetails().getCity());
			
			boolean containsAll = true;
			for (String keyword : keywords) {
				if (!userData.toString().toLowerCase().contains(keyword.toLowerCase())) {
					containsAll = false;
					break;
				}
			}
			if (containsAll)
				foundUsers.add(user);			
		}
		
		return foundUsers;
	}
	
	private boolean emailExists(String email) {
		User user = userRepository.findByEmail(email);
		return user != null;
	}
	
	private boolean usernameExists(String username) {
		User user = userRepository.findByUsername(username);
		return user != null;
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