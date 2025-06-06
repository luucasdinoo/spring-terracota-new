package com.terracota.application.company.management.remove;

public record RemoveCraftsmanCommand(
        String companyId,
        String craftsmanId
) {
    public static RemoveCraftsmanCommand with(final String companyId, final String craftsmanId) {
        return new RemoveCraftsmanCommand(companyId, craftsmanId);
    }
}
