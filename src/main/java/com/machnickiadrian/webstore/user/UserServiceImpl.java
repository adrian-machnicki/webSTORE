package com.machnickiadrian.webstore.user;

import com.machnickiadrian.webstore.email.EmailService;
import com.machnickiadrian.webstore.user.dto.UserDto;
import com.machnickiadrian.webstore.user.dto.UserProfileDto;
import com.machnickiadrian.webstore.user.dto.UserRegisterDto;
import com.machnickiadrian.webstore.user.entity.Role;
import com.machnickiadrian.webstore.user.entity.User;
import com.machnickiadrian.webstore.user.entity.UserDetails;
import com.machnickiadrian.webstore.user.exception.EmailExistsException;
import com.machnickiadrian.webstore.user.exception.UsernameExistsException;
import com.machnickiadrian.webstore.user.mapper.UserRegisterDtoToUserMapper;
import com.machnickiadrian.webstore.user.mapper.UserToUserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Implementation of <code>UserService</code> interface. It's methods are
 * transactional and secured with Spring Security and JSR-250.
 *
 * @author Adrian Machnicki
 */
@Service
public class UserServiceImpl implements UserService {

    private static final int USER_ENABLED = 1;
    private static final int USER_DISABLED = 0;
    private static final String ADMIN = "ROLE_ADMIN";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final BCryptPasswordEncoder encoder;
    private final UserRegisterDtoToUserMapper userRegisterDtoToUserMapper;
    private final UserToUserDtoMapper userToUserDtoMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           EmailService emailService, BCryptPasswordEncoder encoder,
                           UserRegisterDtoToUserMapper userRegisterDtoToUserMapper,
                           UserToUserDtoMapper userToUserDtoMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.encoder = encoder;
        this.userRegisterDtoToUserMapper = userRegisterDtoToUserMapper;
        this.userToUserDtoMapper = userToUserDtoMapper;
    }

    @Transactional
    @Override
    public void registerNewUser(UserRegisterDto userDto) {

        if (emailExists(userDto.getEmail())) {
            throw new EmailExistsException();
        }
        if (usernameExists(userDto.getUsername())) {
            throw new UsernameExistsException();
        }

        User userToSave = createUserFromRegistration(userDto);
        userRepository.save(userToSave);
        emailService.sendRegistrationConfirmation(userDto);
    }

    @PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.username == principal.username")
    @Transactional(readOnly = true)
    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id);
        return userToUserDtoMapper.convert(user);
    }

    @PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.username == principal.username")
    @Transactional(readOnly = true)
    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return userToUserDtoMapper.convert(user);
    }

    @PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.username == principal.username")
    @Transactional(readOnly = true)
    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return userToUserDtoMapper.convert(user);
    }

    @RolesAllowed(ADMIN)
    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userToUserDtoMapper::convert)
                .collect(Collectors.toList());
    }

    public void save(UserProfileDto userProfileDto) {
        User user = userRepository.findById(userProfileDto.getId());
        user.setFirstName(userProfileDto.getFirstName());
        user.setSecondName(userProfileDto.getSecondName());
        user.setLastName(userProfileDto.getLastName());
        user.setEmail(userProfileDto.getEmail());

        if (user.getUserDetails() == null)
            user.setUserDetails(new UserDetails());

        user.getUserDetails().setAddress(userProfileDto.getAddress());
        user.getUserDetails().setCity(userProfileDto.getCity());
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
        if (user.getEnabled() == USER_DISABLED)
            user.setEnabled(USER_ENABLED);
        else
            user.setEnabled(USER_DISABLED);

        userRepository.save(user);
    }

    @RolesAllowed(ADMIN)
    @Transactional(readOnly = true)
    @Override
    public List<UserDto> search(String phrase) {
        phrase = phrase.trim();
        String[] keywords = phrase.split(" ");
        List<UserDto> allUsers = userRepository.findAll().stream()
                .map(userToUserDtoMapper::convert)
                .collect(Collectors.toList());
        List<UserDto> foundUsers = new ArrayList<>();
        StringJoiner userData;

        for (UserDto user : allUsers) {
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

    private User createUserFromRegistration(UserRegisterDto userDto) {
        Role role = new Role(userDto.getUsername(), "ROLE_USER");

        User user = userRegisterDtoToUserMapper.convert(userDto);
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setRoles(Arrays.asList(role));
        user.setEnabled(USER_ENABLED);
        return user;
    }

    private boolean emailExists(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    private boolean usernameExists(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

}