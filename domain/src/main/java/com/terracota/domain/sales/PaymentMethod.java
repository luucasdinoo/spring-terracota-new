package com.terracota.domain.sales;

import java.util.Arrays;
import java.util.Optional;

public enum PaymentMethod {
    PIX("pix"),
    CREDIT_CARD("credit_card");

    private final String value;

    PaymentMethod(final String value) {
        this.value = value;
    }

    public static Optional<PaymentMethod> of(final String inputType){
        return Arrays.stream(PaymentMethod.values())
                .filter(type -> type.value.equalsIgnoreCase(inputType))
                .findFirst();
    }

    public String getValue() {
        return this.value;
    }
}
