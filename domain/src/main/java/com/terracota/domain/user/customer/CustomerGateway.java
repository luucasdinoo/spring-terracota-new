package com.terracota.domain.user.customer;

import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;

import java.util.Optional;

public interface CustomerGateway {

    Customer create(Customer aCustomer);

    void deleteById(CustomerID anId);

    Optional<Customer> findById(CustomerID anId);

    Optional<Customer> findByEmail(String email);

    Customer update(Customer aCustomer);

    Pagination<Customer> list(SearchQuery aQuery);

    String hashPassword(String password);
}
