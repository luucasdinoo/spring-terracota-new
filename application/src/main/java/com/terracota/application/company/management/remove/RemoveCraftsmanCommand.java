package com.terracota.application.company.management.remove;

public record RemoveCraftsmanCommand(
        String craftsmanId
) {
    public static RemoveCraftsmanCommand with( final String craftsmanId) {
        return new RemoveCraftsmanCommand(craftsmanId);
    }
}
