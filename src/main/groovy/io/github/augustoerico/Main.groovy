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
        def input =
                '''"John Doe" "Canada/Ontario/Toronto" 1.5
"Samanta Smith" "Canada/Ontario/London" 3.7
"Adam Xin" "Canada/British Columbia/Vancouver" 2.110
"Monica Taylor" "Canada/Ontario/Toronto" 2.110
"Alicia Yazzie" "US/Arizona/Phoenix" 5.532
"Mohammed Zadeh" "Canada/Ontario/Toronto" 1.43

"John Doe" "Canada"
"John Doe" "Canada/Ontario"
"Alicia Yazzie" "US/Arizona"'''

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
