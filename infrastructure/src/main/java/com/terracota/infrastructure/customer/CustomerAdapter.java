package com.terracota.infrastructure.customer;

import com.terracota.customer.Customer;
import com.terracota.customer.CustomerGateway;
import com.terracota.exceptions.EntityNotFoundException;
import com.terracota.infrastructure.customer.persistence.CustomerModel;
import com.terracota.infrastructure.customer.persistence.CustomerRepository;
import com.terracota.pagination.Pagination;
import com.terracota.pagination.SearchQuery;
import com.terracota.user.UserID;
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
    public void deleteById(final UserID anId) {
        if (!this.customerRepository.existsById(anId.getValue())){
            throw EntityNotFoundException.with(Customer.class, anId);
        }
        this.customerRepository.deleteById(anId.getValue());
    }

    @Override
    public Optional<Customer> findById(final UserID anId) {
        return this.customerRepository.findById(anId.getValue())
                .map(CustomerModel::toDomain);
    }

    @Override
    public Customer update(final Customer aCustomer) {
        return save(aCustomer);
    }

    @Override
    public Pagination<Customer> findAll(final SearchQuery aQuery) {
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
