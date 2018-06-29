package com.machnickiadrian.webstore.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity representing user role. They are used for security reasons.
 *
 * @author Adrian Machnicki
 */
@Entity
@Table(name = "authorities")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String authority;

    public Role() {
    }

    public Role(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", authority='").append(authority).append('\'');
        sb.append('}');
        return sb.toString();
    }
}