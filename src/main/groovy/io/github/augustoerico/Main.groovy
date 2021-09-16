package io.github.augustoerico

import io.github.augustoerico.models.Customer
import io.github.augustoerico.models.Query
import io.github.augustoerico.parsers.CustomerParser
import io.github.augustoerico.parsers.InputParser
import io.github.augustoerico.parsers.QueryParser
import io.github.augustoerico.services.CustomersSvc
import io.github.augustoerico.services.RatingsSvc
import io.github.augustoerico.services.repositories.CustomersRepository

class Main {
    static void main(String... args) {
        if (!args.size()) {
            def message = '[`--args` missing] > Check README.md'
            throw new RuntimeException(message)
        }

        def input = new File(args[0]).text

        def customersSvc = new CustomersSvc(
                new CustomersRepository()
        )
        def ratingsSvc = new RatingsSvc(customersSvc)
        def result = run(input, customersSvc, ratingsSvc)
        println result.join('\n')
    }

    static run(String input, CustomersSvc customersSvc, RatingsSvc ratingsSvc) {
        def lines = InputParser.parse(input)
        lines.customersLines.collect {
            def customer = CustomerParser.toCustomer(it)
            customersSvc.create(customer)
        }
        def queries = lines.queryLines.collect { QueryParser.toQuery(it) }
        def result = ratingsSvc.readMany(queries)
        result
    }
}
