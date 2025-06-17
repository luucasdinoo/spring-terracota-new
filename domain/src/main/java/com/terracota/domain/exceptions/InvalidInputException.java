package com.terracota.domain.exceptions;

public class InvalidInputException extends DomainException{

    protected InvalidInputException(String msg) {
        super(msg);
    }

    public static InvalidInputException with(String msg){
        return new InvalidInputException(msg);
    }
}
