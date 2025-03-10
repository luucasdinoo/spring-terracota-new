package com.terracota.customer;

import com.terracota.pagination.Pagination;
import com.terracota.pagination.SearchQuery;
import com.terracota.user.UserID;

import java.util.Optional;

public interface CustomerGateway {

    Customer create(Customer aCustomer);

    void deleteById(UserID anId);

    Optional<Customer> findById(UserID anId);

    Customer update(Customer aCustomer);

    Pagination<Customer> findAll(SearchQuery aQuery);

}
