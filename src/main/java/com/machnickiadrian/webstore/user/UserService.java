package com.machnickiadrian.webstore.user;

import com.machnickiadrian.webstore.user.dto.UserDto;
import com.machnickiadrian.webstore.user.dto.UserProfileDto;
import com.machnickiadrian.webstore.user.dto.UserRegisterDto;

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