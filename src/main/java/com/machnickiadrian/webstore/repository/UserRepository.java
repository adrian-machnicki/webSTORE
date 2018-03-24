package com.machnickiadrian.webstore.repository;

import org.springframework.data.repository.Repository;

import com.machnickiadrian.webstore.entity.User;

public interface UserRepository extends Repository<User, Long> {

	User findByEmail(String email);

	User save(User user);

	User findByUsername(String username);

}
