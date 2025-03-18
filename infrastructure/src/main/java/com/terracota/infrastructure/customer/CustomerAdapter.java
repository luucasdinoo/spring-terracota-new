package com.terracota.infrastructure.customer;

import com.terracota.customer.Customer;
import com.terracota.customer.CustomerGateway;
import com.terracota.pagination.Pagination;
import com.terracota.pagination.SearchQuery;
import com.terracota.user.UserID;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerAdapter implements CustomerGateway {

    @Override
    public Customer create(final Customer aCustomer) {
        return null;
    }

    @Override
    public void deleteById(final UserID anId) {

    }

    @Override
    public Optional<Customer> findById(final UserID anId) {
        return Optional.empty();
    }

    @Override
    public Customer update(final Customer aCustomer) {
        return null;
    }

    @Override
    public Pagination<Customer> findAll(final SearchQuery aQuery) {
        return null;
    }
}
