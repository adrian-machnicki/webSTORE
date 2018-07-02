package com.machnickiadrian.webstore.service;

import com.machnickiadrian.webstore.converter.UserRegisterDtoToUserConverter;
import com.machnickiadrian.webstore.converter.UserToUserDtoConverter;
import com.machnickiadrian.webstore.dto.UserDetailsDto;
import com.machnickiadrian.webstore.dto.UserDto;
import com.machnickiadrian.webstore.dto.UserProfileDto;
import com.machnickiadrian.webstore.dto.UserRegisterDto;
import com.machnickiadrian.webstore.entity.Role;
import com.machnickiadrian.webstore.entity.User;
import com.machnickiadrian.webstore.exception.EmailExistsException;
import com.machnickiadrian.webstore.exception.UsernameExistsException;
import com.machnickiadrian.webstore.repository.RoleRepository;
import com.machnickiadrian.webstore.repository.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * @author Adrian Machnicki
 */
public class UserServiceImplTest {

    UserService userService;

    @Mock
    UserRepository userRepositoryMock;

    @Mock
    RoleRepository roleRepositoryMock;

    @Mock
    EmailService emailServiceMock;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoderMock;

    @Mock
    UserRegisterDtoToUserConverter userRegisterDtoToUserConverterMock;

    @Mock
    UserToUserDtoConverter userToUserDtoConverterMock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl(userRepositoryMock, roleRepositoryMock, emailServiceMock,
                bCryptPasswordEncoderMock, userRegisterDtoToUserConverterMock, userToUserDtoConverterMock);
    }

    @Test(expected = EmailExistsException.class)
    public void registerNewUser_ShouldThrowExceptionWhenEmailExistsAlready() {
        // given
        String userEmail = "ak@email.com";
        UserRegisterDto userDto = new UserRegisterDto();
        userDto.setUsername("adamkowalski");
        userDto.setEmail(userEmail);

        User existingUser = new User();
        existingUser.setUsername("adamadam");
        existingUser.setEmail(userEmail);

        given(userRepositoryMock.findByEmail(userEmail))
                .willReturn(existingUser);

        // when
        userService.registerNewUser(userDto);

        // then
        // EmailExistsException expected
        verify(userRepositoryMock, times(1))
                .findByEmail(userEmail);
    }

    @Test(expected = UsernameExistsException.class)
    public void registerNewUser_ShouldThrowExceptionWhenUsernameExistsAlready() {
        String username = "adamkowalski";
        UserRegisterDto userDto = new UserRegisterDto();
        userDto.setUsername(username);
        userDto.setEmail("ak@email.com");

        User existingUser = new User();
        existingUser.setUsername(username);
        existingUser.setEmail("adam@email.com");

        given(userRepositoryMock.findByUsername(username))
                .willReturn(existingUser);

        // when
        userService.registerNewUser(userDto);

        // then
        // UsernameExistsException expected
        verify(userRepositoryMock, times(1))
                .findByUsername(username);
    }

    @Test
    public void registerNewUser_ShouldNotThrowAnyExceptionIfUsernameOrEmailNotExists() {
        // given
        String username = "adamkowalski";
        String email = "ak@email.com";
        UserRegisterDto dto = new UserRegisterDto();
        dto.setUsername(username);
        dto.setEmail(email);

        given(userRepositoryMock.findByEmail(email))
                .willReturn(null);
        given(userRepositoryMock.findByUsername(username))
                .willReturn(null);
        given(userRegisterDtoToUserConverterMock.convert(dto))
                .willReturn(new User());

        // when
        userService.registerNewUser(dto);

        // then
        verify(userRepositoryMock, times(1)).findByEmail(email);
        verify(userRepositoryMock, times(1)).findByUsername(username);
        verify(userRepositoryMock, times(1)).save(any());
        verify(emailServiceMock, times(1)).sendRegistrationConfirmation(any());
        verifyNoMoreInteractions(userRepositoryMock, emailServiceMock);
    }

    @Test
    public void findById_ShouldFindUserIfUserExists() {
        // given
        Long id = 1L;
        User user = new User();
        user.setId(id);
        UserDto userDto = new UserDto();
        userDto.setId(id);

        given(userRepositoryMock.findById(id))
                .willReturn(user);
        given(userToUserDtoConverterMock.convert(user))
                .willReturn(userDto);

        // when
        UserDto actualUser = userService.findById(id);

        // then
        assertNotNull("Should return userDto object", actualUser);
        verify(userRepositoryMock, times(1))
                .findById(id);
        verify(userToUserDtoConverterMock, times(1))
                .convert(user);
    }

    @Test
    public void findByUsername_ShouldFindUserIfUserExists() {
        // given
        String username = "username";
        User user = new User();
        user.setUsername(username);
        UserDto userDto = new UserDto();
        userDto.setUsername(username);

        given(userRepositoryMock.findByUsername(username))
                .willReturn(user);
        given(userToUserDtoConverterMock.convert(user))
                .willReturn(userDto);

        // when
        UserDto actualUser = userService.findByUsername(username);

        // then
        assertNotNull("Should return userDto object", actualUser);
        verify(userRepositoryMock, times(1))
                .findByUsername(username);
        verify(userToUserDtoConverterMock, times(1))
                .convert(user);
    }

    @Test
    public void findByEmail_ShouldFindUserIfUserExists() {
        // given
        String email = "test@email.com";
        User user = new User();
        user.setEmail(email);
        UserDto userDto = new UserDto();
        userDto.setEmail(email);

        given(userRepositoryMock.findByEmail(email))
                .willReturn(user);
        given(userToUserDtoConverterMock.convert(user))
                .willReturn(userDto);

        // when
        UserDto actualUser = userService.findByEmail(email);

        // then
        assertNotNull("Should return userDto object", actualUser);
        verify(userRepositoryMock, times(1))
                .findByEmail(email);
        verify(userToUserDtoConverterMock, times(1))
                .convert(user);
    }

    @Test
    public void findAll_ShouldReturnUsersList() {
        // given
        User user1 = new User();
        User user2 = new User();
        UserDto userDto1 = new UserDto();
        UserDto userDto2 = new UserDto();

        given(userRepositoryMock.findAll())
                .willReturn(Arrays.asList(user1, user2));
        given(userToUserDtoConverterMock.convert(user1))
                .willReturn(userDto1);
        given(userToUserDtoConverterMock.convert(user2))
                .willReturn(userDto2);

        // when
        List<UserDto> actualUsers = userService.findAll();

        // then
        assertEquals("Should return list containing two users", 2, actualUsers.size());
        verify(userRepositoryMock, times(1))
                .findAll();
        verify(userToUserDtoConverterMock, times(2))
                .convert(any(User.class));
    }

    @Test
    public void save_shouldCallRepository() {
        // given
        Long id = 1L;
        UserProfileDto userDto = new UserProfileDto();
        userDto.setId(id);
        User user = new User();

        given(userRepositoryMock.findById(id))
                .willReturn(user);

        // when
        userService.save(userDto);

        // then
        verify(userRepositoryMock, times(1))
                .findById(id);
        verify(userRepositoryMock, times(1))
                .save(user);
    }

    @Test
    public void deleteById_ShouldCallRepository() {
        // given
        Long id = 1L;
        String username = "username";
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        List<Role> roles = new ArrayList<>(Arrays.asList(new Role(), new Role()));

        given(userRepositoryMock.findById(id))
                .willReturn(user);
        given(roleRepositoryMock.findByUsername(username))
                .willReturn(roles);

        // when
        userService.deleteById(id);

        // then
        verify(userRepositoryMock, times(1)).findById(user.getId());
        verify(roleRepositoryMock, times(1)).findByUsername(user.getUsername());
        verify(roleRepositoryMock, times(2)).delete(any());
        verify(roleRepositoryMock, times(1)).count();
        verify(userRepositoryMock, times(1)).deleteById(user.getId());
    }

    @Test
    public void setEnabledDisabled_ShouldSetUserDisabledIfWasEnabled() {
        // given
        int userEnabled = 1;
        int userDisabled = 0;
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setEnabled(userEnabled);

        given(userRepositoryMock.findById(userId))
                .willReturn(user);

        // when
        userService.setEnabledDisabled(userId);

        // then
        assertEquals("User should become disabled", userDisabled, user.getEnabled());
        verify(userRepositoryMock, times(1)).findById(userId);
        verify(userRepositoryMock, times(1)).save(user);
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    public void search_ShouldReturnUserList() {
        // given
        String phrase = "doe";
        String username = phrase;
        User user1 = new User();
        user1.setUsername(username);
        User user2 = new User();
        UserDto userDto1 = new UserDto();
        userDto1.setUsername(username);
        userDto1.setUserDetails(new UserDetailsDto());
        UserDto userDto2 = new UserDto();
        userDto2.setUserDetails(new UserDetailsDto());

        given(userRepositoryMock.findAll())
                .willReturn(Arrays.asList(user1, user2));
        given(userToUserDtoConverterMock.convert(user1))
                .willReturn(userDto1);
        given(userToUserDtoConverterMock.convert(user2))
                .willReturn(userDto2);

        // when
        List<UserDto> actualUsers = userService.search(phrase);

        // then
        assertEquals("Should return list containing one user", 1, actualUsers.size());
        verify(userRepositoryMock, times(1))
                .findAll();
        verify(userToUserDtoConverterMock, times(2))
                .convert(any(User.class));
    }
}