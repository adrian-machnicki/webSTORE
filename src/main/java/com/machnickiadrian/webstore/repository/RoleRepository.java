package com.machnickiadrian.webstore.repository;

import java.util.ArrayList;

import org.springframework.data.repository.Repository;

import com.machnickiadrian.webstore.entity.Role;

/**
 * Repository class for <code>Role</code> objects.
 * 
 * @author Adrian Machnicki
 *
 */
public interface RoleRepository extends Repository<Role, Long> {

	ArrayList<Role> findByUsername(String username);
	
	void delete(Role role);
	
	ArrayList<Role> findAll();
	
	int count();

}