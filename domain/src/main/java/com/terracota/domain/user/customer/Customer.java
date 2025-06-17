package com.terracota.domain.user.customer;

import com.terracota.domain.AggregateRoot;
import com.terracota.domain.resource.ImagePhoto;
import com.terracota.domain.user.Address;
import com.terracota.domain.user.CPF;
import com.terracota.domain.user.Role;
import com.terracota.domain.user.User;

import java.time.Instant;
import java.util.Optional;

public class Customer extends AggregateRoot<CustomerID> {

    private final User user;

    private String name;

    private String phone;

    private CPF cpf;

    private boolean active;

    private ImagePhoto photo;

    private Address address;

    private Instant createdAt;

    private Instant updatedAt;

    private Customer(
            final CustomerID customerID,
            final User user,
            final String name,
            final String phone,
            final CPF cpf,
            final boolean isActive,
            final ImagePhoto photo,
            final Address address,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(customerID);
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.cpf = cpf;
        this.active = isActive;
        this.photo = photo;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Customer newCustomer(
            final String email,
            final String password,
            final Role role,
            final String name,
            final String phone,
            final CPF cpf,
            final boolean isActive,
            final Address address
    ){
        final User user = User.newUser(email, password, role);
        final CustomerID id = CustomerID.unique();
        final Instant now = Instant.now();
        return new Customer(id, user, name, phone, cpf, isActive,null, address, now, now);
    }

    public static Customer with(
            final CustomerID customerID,
            final User user,
            final String name,
            final String phone,
            final CPF cpf,
            final boolean isActive,
            final ImagePhoto photo,
            final Address address,
            final Instant createdAt,
            final Instant updatedAt
    ){
        return new Customer(customerID, user, name, phone, cpf, isActive, photo, address, createdAt, updatedAt);
    }

    public void activate(){
        this.active = true;
        this.updatedAt = Instant.now();
    }

    public void deactivate(){
        this.active = false;
        this.updatedAt = Instant.now();
    }

    public Customer update(final String name, final String phone, final boolean isActive){
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

    public User getUser() {
        return user;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public Optional<ImagePhoto> getPhoto() {
        return Optional.ofNullable(photo);
    }

    public Customer setPhoto(final ImagePhoto photo) {
        this.photo = photo;
        this.updatedAt = Instant.now();
        return this;
    }
}
