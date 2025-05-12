package com.terracota.infrastructure.user.customer.persistence;

import com.terracota.domain.user.CPF;
import com.terracota.domain.user.customer.Customer;
import com.terracota.domain.user.customer.CustomerID;
import com.terracota.infrastructure.user.AddressEmbedded;
import com.terracota.infrastructure.user.ImagePhotoModel;
import com.terracota.infrastructure.user.UserEmbedded;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Optional;

@Entity(name = "Customer")
@Table(name = "customers")
@Getter @Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CustomerModel{

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

    public static CustomerModel from(final Customer customer){
        return new CustomerModel(
                customer.getId().getValue(),
                UserEmbedded.from(customer.getUser()),
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
                CustomerID.from(getId()),
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
                getUpdatedAt()
        );
    }
}
