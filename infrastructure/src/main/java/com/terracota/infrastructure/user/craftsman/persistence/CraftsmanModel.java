package com.terracota.infrastructure.user.craftsman.persistence;

import com.terracota.domain.user.CPF;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanID;
import com.terracota.infrastructure.user.AddressEmbedded;
import com.terracota.infrastructure.user.ImagePhotoModel;
import com.terracota.infrastructure.user.UserEmbedded;
import com.terracota.infrastructure.user.company.CompanyModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Optional;

@Entity(name = "Craftsman")
@Table(name = "craftsmen")
@Getter @Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
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

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyModel company;

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
                craftsman.getUpdatedAt(),
                craftsman.getCompany().isPresent() ? CompanyModel.from(craftsman.getCompany().get()) : null
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
                Optional.ofNullable(getPhoto())
                        .map(ImagePhotoModel::toDomain)
                        .orElse(null),
                Optional.ofNullable(getAddress())
                        .map(AddressEmbedded::toDomain)
                        .orElse(null),
                getCreatedAt(),
                getUpdatedAt(),
                Optional.ofNullable(getCompany())
                        .map(CompanyModel::toDomain)
                        .orElse(null)
        );
    }
}
