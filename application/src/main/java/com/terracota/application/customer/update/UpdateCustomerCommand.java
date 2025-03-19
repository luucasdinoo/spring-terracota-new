package com.terracota.application.customer.update;

public record UpdateCustomerCommand(
        String id,
        String name,
        String phone,
        boolean isActive
) {

    public static UpdateCustomerCommand with(
            final String id,
            final String name,
            final String phone,
            final boolean isActive
    ){
        return new UpdateCustomerCommand(id, name, phone, isActive);
    }
}
