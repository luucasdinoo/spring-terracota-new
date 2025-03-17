package com.terracota.exceptions;

public class DomainException extends NoStackTraceException{

    protected DomainException(final String msg) {
        super(msg);
    }

    public static DomainException with(String msg){
        return new DomainException(msg);
    }
}
