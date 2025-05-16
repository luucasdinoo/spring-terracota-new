package com.terracota.domain.sales;

public enum PaymentMethod {
    PIX("pix");

    private final String value;

    PaymentMethod(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
