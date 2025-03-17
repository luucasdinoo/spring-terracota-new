package com.terracota.exceptions;

import com.terracota.AggregateRoot;
import com.terracota.Identifier;

public class EntityNotFoundException extends DomainException {

    protected EntityNotFoundException(String msg) {
        super(msg);
    }

    public static EntityNotFoundException with(
                    final Class<? extends AggregateRoot<?>> aggregate,
                    final Identifier id
    ) {
        final String msg = "%s with ID %s was not found".formatted(aggregate.getSimpleName(), id.getValue());
        return new EntityNotFoundException(msg);
    }

    public static EntityNotFoundException with(final String msg){
        return new EntityNotFoundException(msg);
    }
}
