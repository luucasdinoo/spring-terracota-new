package com.terracota.infrastructure.customer.persistence;

import com.terracota.customer.CPF;
import com.terracota.customer.Customer;
import com.terracota.infrastructure.user.persistence.UserModel;
import com.terracota.user.UserID;
import com.terracota.user.UserRole;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Optional;

@Entity(name = "Customer")
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "customer_id")
public class CustomerModel extends UserModel {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = "active", nullable = false)
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "photo_id")
    private ImagePhotoModel photo;

    @Embedded
    private AddressEmbedded address;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public CustomerModel(){}

    private CustomerModel(
            final String email,
            final String password,
            final UserRole role,
            final String name,
            final String phone,
            final String cpf,
            final boolean active,
            final ImagePhotoModel photo,
            final AddressEmbedded address,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(email, password, role);
        this.name = name;
        this.phone = phone;
        this.cpf = cpf;
        this.active = active;
        this.photo = photo;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CustomerModel from(final Customer customer){
        return new CustomerModel(
                customer.getEmail(),
                customer.getPassword(),
                customer.getRole(),
                customer.getName(),
                customer.getPhone(),
                customer.getCpf().getValue(),
                customer.isActive(),
                customer.getPhoto()
                        .map(ImagePhotoModel::from)
                        .orElse(null),
                customer.getAddress()
                        .map(AddressEmbedded::from)
                        .orElse(null),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

    public Customer toDomain(){
        return Customer.with(
                UserID.from(getId()),
                getEmail(),
                getPassword(),
                getRole(),
                getName(),
                getPhone(),
                CPF.from(getCpf()),
                isActive(),
                Optional.ofNullable(getPhoto())
                        .map(ImagePhotoModel::toDomain)
                        .orElse(null),
                Optional.ofNullable(getAddress())
                        .map(AddressEmbedded::toDomain)
                        .orElse(null),
                getCreatedAt(),
                getUpdatedAt()
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ImagePhotoModel getPhoto() {
        return photo;
    }

    public void setPhoto(ImagePhotoModel photo) {
        this.photo = photo;
    }

    public AddressEmbedded getAddress() {
        return address;
    }

    public void setAddress(AddressEmbedded address) {
        this.address = address;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
