package com.terracota.user;

import com.terracota.AggregateRoot;

public abstract class User extends AggregateRoot<UserID> {

    private String email;

    private String password;

    private UserRole role;

    protected User(
            final UserID userID,
            final String email,
            final String password,
            final UserRole role
    ) {
        super(userID);
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public UserID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }
}
