package com.terracota.application.company.retrieve.get;

import com.terracota.domain.resource.ImagePhoto;
import com.terracota.domain.user.company.Company;

import java.time.Instant;

public record CompanyOutput(
    String id,
    String ownerEmail,
    String legalName,
    String tradeName,
    String cnpj,
    String phone,
    boolean isActive,
    String photo,
    Instant createdAt,
    Instant updatedAt
) {
    public static CompanyOutput from(final Company company) {
        return new CompanyOutput(
            company.getId().getValue(),
            company.getOwner().getEmail(),
            company.getLegalName(),
            company.getTradeName(),
            company.getCnpj().getValue(),
            company.getPhone(),
            company.isActive(),
            company.getPhoto()
                    .map(ImagePhoto::location)
                    .orElse(null),
            company.getCreatedAt(),
            company.getUpdatedAt()
        );
    }
}
