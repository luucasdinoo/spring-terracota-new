package com.terracota.infrastructure.user.customer.presenter;

import com.terracota.application.customer.retrieve.get.CustomerOutput;
import com.terracota.application.customer.retrieve.list.ListCustomerOutput;
import com.terracota.domain.user.Role;
import com.terracota.infrastructure.user.customer.models.CustomerResponse;
import com.terracota.infrastructure.user.customer.models.ListCustomerResponse;

public interface CustomerPresenter {

    static CustomerResponse present(final CustomerOutput output){
        return new CustomerResponse(
                output.id(),
                output.email(),
                Role.valueOf(output.role()),
                output.name(),
                output.phone(),
                output.cpf(),
                output.isActive(),
                output.photo(),
                output.address(),
                output.createdAt(),
                output.updatedAt()
        );
    }

    static ListCustomerResponse present(final ListCustomerOutput output){
        return new ListCustomerResponse(
                output.id(),
                output.email(),
                Role.valueOf(output.role())
        );
    }
}
