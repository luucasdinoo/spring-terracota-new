package com.terracota.infrastructure.user.craftsman.persistence;

import com.terracota.domain.user.CPF;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanID;
import com.terracota.infrastructure.user.AddressEmbedded;
import com.terracota.infrastructure.user.ImagePhotoModel;
import com.terracota.infrastructure.user.UserEmbedded;
import jakarta.persistence.*;

import java.time.Instant;

@Entity(name = "Craftsman")
@Table(name = "craftsmen")
public class CraftsmanModel {

    @Id
    private String id;

    @Embedded
    private UserEmbedded user;

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

    public CraftsmanModel(){}

    private CraftsmanModel(
            final String id,
            final UserEmbedded user,
            final String name,
            final String phone,
            final String cpf,
            final boolean active,
            final ImagePhotoModel photo,
            final AddressEmbedded address,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.cpf = cpf;
        this.active = active;
        this.photo = photo;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CraftsmanModel from(final Craftsman craftsman) {
        return new CraftsmanModel(
                craftsman.getId().getValue(),
                UserEmbedded.from(craftsman.getUser()),
                craftsman.getName(),
                craftsman.getPhone(),
                craftsman.getCpf().getValue(),
                craftsman.isActive(),
                craftsman.getPhoto()
                        .map(ImagePhotoModel::from)
                        .orElse(null),
                craftsman.getAddress()
                        .map(AddressEmbedded::from)
                        .orElse(null),
                craftsman.getCreatedAt(),
                craftsman.getUpdatedAt()
        );
    }

    public Craftsman toDomain(){
        return Craftsman.with(
                CraftsmanID.from(getId()),
                getUser().toDomain(),
                getName(),
                getPhone(),
                CPF.from(getCpf()),
                isActive(),
                getPhoto().toDomain(),
                getAddress().toDomain(),
                getCreatedAt(),
                getUpdatedAt()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEmbedded getUser() {
        return user;
    }

    public void setUser(UserEmbedded user) {
        this.user = user;
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
