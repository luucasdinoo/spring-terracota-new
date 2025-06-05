package com.terracota.domain.user.company;

import com.terracota.domain.AggregateRoot;
import com.terracota.domain.resource.ImagePhoto;
import com.terracota.domain.user.CNPJ;
import com.terracota.domain.user.Role;
import com.terracota.domain.user.User;
import com.terracota.domain.user.craftsman.Craftsman;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Company extends AggregateRoot<CompanyID> {

    private final User owner;

    private String legalName;

    private String tradeName;

    private CNPJ cnpj;

    private String phone;

    private boolean active;

    private ImagePhoto photo;

    private Instant createdAt;

    private Instant updatedAt;

    private Set<Craftsman> craftsmen;

    private Company(
            final CompanyID companyID,
            final User owner,
            final String legalName,
            final String tradeName,
            final CNPJ cnpj,
            final String phone,
            final boolean active,
            final ImagePhoto photo,
            final Instant createdAt,
            final Instant updatedAt,
            final Set<Craftsman> craftsmen
    ) {
        super(companyID);
        this.owner = owner;
        this.legalName = legalName;
        this.tradeName = tradeName;
        this.cnpj = cnpj;
        this.phone = phone;
        this.active = active;
        this.photo = photo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.craftsmen = new HashSet<>();
    }

    public static Company newCustomer(
            final String email,
            final String password,
            final Role role,
            final String legalName,
            final String tradeName,
            final CNPJ cnpj,
            final String phone,
            final boolean isActive
    ){
        final User owner = User.newUser(email, password, role);
        final CompanyID id = CompanyID.unique();
        final Instant now = Instant.now();
        return new Company(id, owner, legalName, tradeName, cnpj, phone, isActive, null, now, now, new HashSet<>());
    }

    public static Company with(
            final CompanyID customerID,
            final User owner,
            final String legalName,
            final String tradeName,
            final CNPJ cnpj,
            final String phone,
            final boolean isActive,
            final ImagePhoto photo,
            final Instant createdAt,
            final Instant updatedAt,
            final Set<Craftsman> craftsmen
    ){
        return new Company(customerID, owner, legalName, tradeName, cnpj, phone, isActive, photo, createdAt, updatedAt, craftsmen);
    }

    public void activate(){
        this.active = true;
        this.updatedAt = Instant.now();
    }

    public void deactivate(){
        this.active = false;
        this.updatedAt = Instant.now();
    }

    public Company update(final String legalName, final String tradeName, final String phone, final boolean isActive){
        if (isActive){
            activate();
        } else {
            deactivate();
        }
        this.legalName = legalName;
        this.tradeName = tradeName;
        this.phone = phone;
        this.updatedAt = Instant.now();
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public String getLegalName() {
        return legalName;
    }

    public String getTradeName() {
        return tradeName;
    }

    public CNPJ getCnpj() {
        return cnpj;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isActive() {
        return active;
    }

    public Optional<ImagePhoto> getPhoto() {
        return Optional.ofNullable(photo);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Set<Craftsman> getCraftsmen() {
        return craftsmen != null ? Collections.unmodifiableSet(craftsmen) : Collections.emptySet();
    }
}
