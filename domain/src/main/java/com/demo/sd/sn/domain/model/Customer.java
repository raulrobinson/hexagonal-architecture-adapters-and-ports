package com.demo.sd.sn.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Customer {

    private final CustomerId id;
    private String name;
    private String email;
    private final LocalDateTime createdAt;

    public Customer(CustomerId id, String name, String email, LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static Customer createNew(String name, String email) {
        return new Customer(CustomerId.newId(), name, email, LocalDateTime.now());
    }

    public CustomerId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void rename(String newName) {
        this.name = Objects.requireNonNull(newName);
    }

    public String getEmail() {
        return email;
    }

    public void changeEmail(String newEmail) {
        this.email = Objects.requireNonNull(newEmail);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

