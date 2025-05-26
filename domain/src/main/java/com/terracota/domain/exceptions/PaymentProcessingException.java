package com.terracota.domain.exceptions;

public class PaymentProcessingException extends DomainException {

    private PaymentProcessingException(String message) {
        super(message);
    }

    public static EntityNotFoundException with(final String msg){
        return new EntityNotFoundException(msg);
    }
}
