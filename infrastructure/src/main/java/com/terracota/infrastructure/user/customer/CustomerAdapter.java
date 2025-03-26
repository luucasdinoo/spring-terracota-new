package com.terracota.infrastructure.user.customer;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;
import com.terracota.domain.user.customer.Customer;
import com.terracota.domain.user.customer.CustomerGateway;
import com.terracota.domain.user.customer.CustomerID;
import com.terracota.infrastructure.user.customer.persistence.CustomerModel;
import com.terracota.infrastructure.user.customer.persistence.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class CustomerAdapter implements CustomerGateway {

    private final CustomerRepository customerRepository;

    public CustomerAdapter(final CustomerRepository customerRepository) {
        this.customerRepository = Objects.requireNonNull(customerRepository);
    }

    @Override
    public Customer create(final Customer aCustomer) {
        return save(aCustomer);
    }

    @Override
    public void deleteById(final CustomerID anId) {
        if (!this.customerRepository.existsById(anId.getValue())){
            throw EntityNotFoundException.with(Customer.class, anId);
        }
        this.customerRepository.deleteById(anId.getValue());
    }

    @Override
    public Optional<Customer> findById(final CustomerID anId) {
        return this.customerRepository.findById(anId.getValue())
                .map(CustomerModel::toDomain);
    }

    @Override
    public Customer update(final Customer aCustomer) {
        return save(aCustomer);
    }

    @Override
    public Pagination<Customer> list(final SearchQuery aQuery) {
        PageRequest page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        Page<CustomerModel> pageResult = this.customerRepository.findAll(page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(CustomerModel::toDomain).toList()
        );
    }

    private Customer save(Customer aCustomer) {
        return this.customerRepository.save(CustomerModel.from(aCustomer))
                .toDomain();
    }
}
