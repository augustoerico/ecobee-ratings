package io.github.augustoerico.services

import io.github.augustoerico.models.Customer
import io.github.augustoerico.models.Query
import io.github.augustoerico.services.repositories.CustomersRepository

class CustomersSvc {
    CustomersRepository repository

    CustomersSvc(CustomersRepository repository) {
        this.repository = repository
    }

    void create(Customer customer) {
        this.repository.insert(customer)
    }

    Customer read(String name) {
        this.repository.fetch(name)
    }

    List<Customer> readMany(Query query) {
        this.repository.fetchMany(query)
    }
}
