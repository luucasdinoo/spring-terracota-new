package com.terracota.infrastructure.sales.models;

import com.terracota.domain.sales.SaleItem;

import java.util.Set;

public record GenerateLinkRequest(Set<SaleItem> items) {
}
