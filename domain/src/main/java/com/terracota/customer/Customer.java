package com.terracota.customer;

import com.terracota.user.User;
import com.terracota.user.UserID;
import com.terracota.user.UserRole;
import com.terracota.validation.ValidationHandler;

import java.time.Instant;

public class Customer extends User {

    private String name;

    private String phone;

    private CPF cpf;

    private boolean active;

    private Address address;

    private Instant createdAt;

    private Instant updatedAt;

    private Customer(
            final UserID userID,
            final String email,
            final String password,
            final UserRole role,
            final String name,
            final String phone,
            final CPF cpf,
            final boolean isActive,
            final Address address,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(userID, email, password, role);
        this.name = name;
        this.phone = phone;
        this.cpf = cpf;
        this.active = isActive;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Customer newCustomer(
            final String email,
            final String password,
            final UserRole role,
            final String name,
            final String phone,
            final CPF cpf,
            final boolean isActive,
            final Address address
    ){
        final UserID id = UserID.unique();
        final Instant now = Instant.now();
        return new Customer(id, email, password, role, name, phone, cpf, isActive, address, now, now);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new CustomerValidator(this, handler).validate();
    }

    public Customer activate(){
        this.active = true;
        this.updatedAt = Instant.now();
        return this;
    }

    public Customer deactivate(){
        this.active = false;
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

    public Address getAddress() {
        return address;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

}
