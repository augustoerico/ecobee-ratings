package io.github.augustoerico.services

import io.github.augustoerico.models.Customer
import io.github.augustoerico.models.Query
import io.github.augustoerico.models.Rating


class RatingsSvc {

    CustomersSvc customersSvc

    RatingsSvc(CustomersSvc customersSvc) {
        this.customersSvc = customersSvc
    }

    List<Rating> readMany(List<Query> queries) {
        queries.collect {this.readMany(it) }
    }

    Rating readMany(Query query) {
        def customers = this.customersSvc.readMany(query)
        def customer = this.customersSvc.read(query.name)
        def higherRValuePercent = (higherRValueCustomers(customer, customers).size()) / (customers.size())
        new Rating(query.name, query.region, score(higherRValuePercent))
    }

    static higherRValueCustomers(Customer refCustomer, List<Customer> customers) {
        customers.findAll({
            it.rValue > refCustomer.rValue
        }).asList()
    }

    static score(Float higherRValuePercent) {
        if (higherRValuePercent >= 0.9) {
            return 1
        } else if (higherRValuePercent >= 0.8) {
            return 2
        } else if (higherRValuePercent >= 0.7) {
            return 3
        } else if (higherRValuePercent >= 0.6) {
            return 4
        } else if (higherRValuePercent >= 0.5) {
            return 5
        } else if (higherRValuePercent >= 0.4) {
            return 6
        } else if (higherRValuePercent >= 0.3) {
            return 7
        } else if (higherRValuePercent >= 0.2) {
            return 8
        } else if (higherRValuePercent >= 0.1) {
            return 9
        } else {
            return 10
        }
    }
}
