package com.terracota.domain.user.craftsman;

import com.terracota.domain.user.*;

import java.time.Instant;
import java.util.Optional;

public class Craftsman extends User {

    private String name;

    private String phone;

    private CPF cpf;

    private boolean active;

    private ImagePhoto photo;

    private Address address;

    private Instant createdAt;

    private Instant updatedAt;

    private Craftsman(
            final UserID userID,
            final String email,
            final String password,
            final Role role,
            final String name,
            final String phone,
            final CPF cpf,
            final boolean isActive,
            final ImagePhoto photo,
            final Address address,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(userID, email, password, role);
        this.name = name;
        this.phone = phone;
        this.cpf = cpf;
        this.active = isActive;
        this.photo = photo;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private Craftsman newCraftsman(
            final String email,
            final String password,
            final Role role,
            final String name,
            final String phone,
            final CPF cpf,
            final boolean isActive
    ){
        final UserID anId = UserID.unique();
        final Instant now = Instant.now();
        return new Craftsman(anId, email, password, role, name, phone, cpf, isActive,null, null, now, now);
    }

    public static Craftsman with(
            final UserID userID,
            final String email,
            final String password,
            final Role role,
            final String name,
            final String phone,
            final CPF cpf,
            final boolean isActive,
            final ImagePhoto photo,
            final Address address,
            final Instant createdAt,
            final Instant updatedAt
    ){
        return new Craftsman(userID, email, password, role, name, phone, cpf, isActive, photo, address, createdAt, updatedAt);
    }

    public void activate(){
        this.active = true;
        this.updatedAt = Instant.now();
    }

    public void deactivate(){
        this.active = false;
        this.updatedAt = Instant.now();
    }

    public Craftsman update(final String name, final String phone, final boolean isActive){
        if (isActive){
            activate();
        } else {
            deactivate();
        }
        this.name = name;
        this.phone = phone;
        this.updatedAt = Instant.now();
        return this;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public CPF getCpf() {
        return cpf;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public Optional<ImagePhoto> getPhoto() {
        return Optional.ofNullable(photo);
    }
}
