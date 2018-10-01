package com.machnickiadrian.webstore.user;

import com.machnickiadrian.webstore.user.entity.User;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Repository class for <code>User</code> objects.
 *
 * @author Adrian Machnicki
 */
public interface UserRepository extends Repository<User, Long> {

    User findByUsername(String username);

    User findById(Long id);

    User findByEmail(String email);

    List<User> findAll();

    void save(User user);

    void deleteById(Long id);

}