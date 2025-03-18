package com.terracota.infrastructure.user.persistence;

import com.terracota.user.UserRole;
import jakarta.persistence.*;

@Entity(name = "User")
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserModel {

    @Id
    private String id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "email", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private UserRole role;

    public UserModel(){}

    protected UserModel(final String email, final String password, final UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
