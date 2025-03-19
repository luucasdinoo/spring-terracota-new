package com.terracota.domain.user;

public class User {

    private String email;

    private String password;

    private Role role;

    private User(
            final String email,
            final String password,
            final Role role
    ) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static User newUser(
            final String email,
            final String password,
            final Role role
    ) {
        return new User(email, password, role);
    }

    public static User with(final String email, final String password, final Role role) {
        return new User(email, password, role);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

}
