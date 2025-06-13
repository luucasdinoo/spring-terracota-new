package com.terracota.domain.pagination.projection;

public record CraftsmenDomainProjection(
        String id,
        String email,
        String role
) {
    public CraftsmenDomainProjection with(final String id, final String email, final String role){
        return new CraftsmenDomainProjection(id, email, role);
    }
}
