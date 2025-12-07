package com.demo.servicedomain.servicename.infrastructure.db;

import com.demo.servicedomain.servicename.domain.model.Customer;
import com.demo.servicedomain.servicename.domain.model.CustomerId;
import com.demo.servicedomain.servicename.domain.port.CustomerRepository;
import com.demo.servicedomain.servicename.infrastructure.db.jpa.CustomerEntity;
import com.demo.servicedomain.servicename.infrastructure.db.jpa.SpringDataCustomerRepository;

import java.util.Optional;

public class CustomerRepositoryAdapter {//implements CustomerRepository {

//    private final SpringDataCustomerRepository repository;
//
//    public CustomerRepositoryAdapter(SpringDataCustomerRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public Customer save(Customer customer) {
//        CustomerEntity entity = new CustomerEntity();
//        entity.setId(customer.getId().getValue());
//        entity.setName(customer.getName());
//        entity.setEmail(customer.getEmail());
//        entity.setCreatedAt(customer.getCreatedAt());
//        repository.save(entity);
//        return customer;
//    }
//
//    @Override
//    public Optional<Customer> findById(CustomerId id) {
//        return repository.findById(id.getValue())
//                .map(this::toDomain);
//    }
//
//    @Override
//    public boolean existsByEmail(String email) {
//        return repository.existsByEmail(email);
//    }
//
//    private Customer toDomain(CustomerEntity entity) {
//        return new Customer(
//                CustomerId.of(entity.getId()),
//                entity.getName(),
//                entity.getEmail(),
//                entity.getCreatedAt()
//        );
//    }
}

