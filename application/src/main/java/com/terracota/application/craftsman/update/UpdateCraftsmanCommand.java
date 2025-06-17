package com.terracota.application.craftsman.update;

public record UpdateCraftsmanCommand(
        String id,
        String name,
        String phone,
        boolean isActive
) {
    public static UpdateCraftsmanCommand with(
            final String id,
            final String name,
            final String phone,
            final boolean isActive
    ){
        return new UpdateCraftsmanCommand(id, name, phone, isActive);
    }
}
