package com.machnickiadrian.webstore.service;

import com.machnickiadrian.webstore.dto.UserDto;
import com.machnickiadrian.webstore.entity.User;
import com.machnickiadrian.webstore.exception.EmailExistsException;
import com.machnickiadrian.webstore.exception.UsernameExistsException;

public interface UserService {
	
	User registerNewUser(UserDto userDto) throws EmailExistsException, UsernameExistsException;

}
