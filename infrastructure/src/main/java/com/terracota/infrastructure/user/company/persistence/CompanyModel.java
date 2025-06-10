package com.terracota.infrastructure.user.company.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.terracota.domain.user.CNPJ;
import com.terracota.domain.user.company.Company;
import com.terracota.domain.user.company.CompanyID;
import com.terracota.infrastructure.user.ImagePhotoModel;
import com.terracota.infrastructure.user.UserEmbedded;
import com.terracota.infrastructure.user.craftsman.persistence.CraftsmanModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "Company")
@Table(name = "companies")
@Getter @Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CompanyModel {

    @Id
    private String id;

    @Embedded
    private UserEmbedded owner;

    @Column(name = "legalName", nullable = false)
    private String legalName;

    @Column(name = "tradeName", nullable = false)
    private String tradeName;

    @Column(name = "cnpj", nullable = false, length = 14, unique = true)
    private String cnpj;

    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "photo_id")
    private ImagePhotoModel photo;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "company")
    private Set<CraftsmanModel> craftsmen;

    public static CompanyModel from(final Company company) {
        return new CompanyModel(
                company.getId().getValue(),
                UserEmbedded.from(company.getOwner()),
                company.getLegalName(),
                company.getTradeName(),
                company.getCnpj().getValue(),
                company.getPhone(),
                company.isActive(),
                company.getPhoto()
                        .map(ImagePhotoModel::from)
                        .orElse(null),
                company.getCreatedAt(),
                company.getUpdatedAt(),
                company.getCraftsmen().stream()
                        .map(CraftsmanModel::from)
                        .collect(Collectors.toSet())
        );
    }

    public Company toDomain() {
        return Company.with(
                CompanyID.from(getId()),
                getOwner().toDomain(),
                getLegalName(),
                getTradeName(),
                CNPJ.from(getCnpj()),
                getPhone(),
                isActive(),
                Optional.ofNullable(getPhoto())
                        .map(ImagePhotoModel::toDomain)
                        .orElse(null),
                getCreatedAt(),
                getUpdatedAt(),
                getCraftsmen().stream()
                        .map(CraftsmanModel::toDomain)
                        .collect(Collectors.toSet())
        );
    }
}
