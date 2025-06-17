package com.terracota.application.company.management.add;

public record AddCraftsmanCommand(
        String companyId,
        String craftsmanId
) {
    public static AddCraftsmanCommand with(final String companyId, final String craftsmanId) {
        return new AddCraftsmanCommand(companyId, craftsmanId);
    }
}
