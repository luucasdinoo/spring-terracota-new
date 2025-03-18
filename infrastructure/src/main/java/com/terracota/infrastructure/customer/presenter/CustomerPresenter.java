package com.terracota.infrastructure.customer.presenter;

import com.terracota.customer.retrieve.get.CustomerOutput;
import com.terracota.customer.retrieve.list.ListCustomerOutput;
import com.terracota.infrastructure.customer.models.CustomerResponse;
import com.terracota.infrastructure.customer.models.ListCustomerResponse;
import com.terracota.user.UserRole;

public interface CustomerPresenter {

    static CustomerResponse present(final CustomerOutput output){
        return new CustomerResponse(
                output.id(),
                output.email(),
                UserRole.valueOf(output.role()),
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
                UserRole.valueOf(output.role())
        );
    }
}
