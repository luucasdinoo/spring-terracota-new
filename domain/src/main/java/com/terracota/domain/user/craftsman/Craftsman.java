package com.terracota.domain.user.craftsman;

import com.terracota.domain.AggregateRoot;
import com.terracota.domain.user.*;

import java.time.Instant;
import java.util.Optional;

public class Craftsman extends AggregateRoot<CraftsmanID> {

    private final User user;

    private String name;

    private String phone;

    private CPF cpf;

    private boolean active;

    private ImagePhoto photo;

    private Address address;

    private Instant createdAt;

    private Instant updatedAt;

    private Craftsman(
            final CraftsmanID craftsmanID,
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
        super(craftsmanID);
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

    public static Craftsman newCraftsman(
            final String email,
            final String password,
            final Role role,
            final String name,
            final String phone,
            final CPF cpf,
            final boolean isActive,
            final ImagePhoto photo,
            final Address address
    ){
        final User user = User.newUser(email, password, role);
        final CraftsmanID anId = CraftsmanID.unique();
        final Instant now = Instant.now();
        return new Craftsman(anId, user , name, phone, cpf, isActive,photo, address, now, now);
    }

    public static Craftsman with(
            final CraftsmanID craftsmanID,
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
        return new Craftsman(craftsmanID, user, name, phone, cpf, isActive, photo, address, createdAt, updatedAt);
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

    public User getUser() {
        return user;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public Optional<ImagePhoto> getPhoto() {
        return Optional.ofNullable(photo);
    }
}
