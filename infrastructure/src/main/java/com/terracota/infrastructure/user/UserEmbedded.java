package com.terracota.infrastructure.user;

import com.terracota.domain.user.Role;
import com.terracota.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class UserEmbedded {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "email", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEmbedded(){}

    private UserEmbedded(final String email, final String password, final Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static UserEmbedded from(final User user){
        return new UserEmbedded(user.getEmail(), user.getPassword(), user.getRole());
    }

    public User toDomain(){
        return User.with(
                getEmail(),
                getPassword(),
                getRole()
        );
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
