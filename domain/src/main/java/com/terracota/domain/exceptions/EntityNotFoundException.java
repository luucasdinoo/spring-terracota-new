package com.terracota.domain.exceptions;

import com.terracota.domain.AggregateRoot;
import com.terracota.domain.Identifier;

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
