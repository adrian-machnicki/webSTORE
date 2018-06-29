package com.machnickiadrian.webstore.service;

import com.machnickiadrian.webstore.dto.UserDto;
import com.machnickiadrian.webstore.dto.UserProfileDto;
import com.machnickiadrian.webstore.dto.UserRegisterDto;

import java.util.List;

/**
 * Service interface for <code>User</code> objects manipulation.
 *
 * @author Adrian Machnicki
 */
public interface UserService {

    void registerNewUser(UserRegisterDto userDto);

    UserDto findByUsername(String username);

    UserDto findByEmail(String email);

    UserDto findById(Long id);

    List<UserDto> findAll();

    void save(UserProfileDto userProfileDto);

    void deleteById(Long id);

    void setEnabledDisabled(Long userId);

    List<UserDto> search(String phrase);

}