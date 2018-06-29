package com.machnickiadrian.webstore.repository;

import com.machnickiadrian.webstore.entity.Role;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Repository class for <code>Role</code> objects.
 *
 * @author Adrian Machnicki
 */
public interface RoleRepository extends Repository<Role, Long> {

    List<Role> findByUsername(String username);

    void delete(Role role);

    List<Role> findAll();

    int count();

}