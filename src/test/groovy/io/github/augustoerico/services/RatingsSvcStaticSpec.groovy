package io.github.augustoerico.services

import io.github.augustoerico.models.Customer
import io.github.augustoerico.models.Location
import spock.lang.Specification

class RatingsSvcStaticSpec extends Specification {

    def 'should return the ratings rank'() {

        expect:
            RatingsSvc.score(higherRValuesPercent) == expectedScore

        where:
            higherRValuesPercent    | expectedScore
            0.99                    | 1
            0.80                    | 2
            0.50                    | 5
            0.11                    | 9
            0.10                    | 9
            0.09                    | 10
    }

    def 'should return the customers with higher r-value'() {
        given:
        Location toronto = new Location('Canada', 'Ontario', 'Toronto')
        Location london = new Location('Canada', 'Ontario', 'London')
        Location vancouver = new Location('Canada', 'British Columbia', 'Vancouver')

        and:
        def refCustomer = new Customer('John Doe', toronto, 1.5)

        and:
        def customers = [
                new Customer('John Doe', toronto, 1.5),
                new Customer('Samanta Smith', london, 3.7),
                new Customer('Adam Xin', vancouver, 2.110),
                new Customer('Monica Taylor', toronto, 2.110),
                new Customer('Mohammed Zadeh', toronto, 1.43)
        ] as List<Customer>

        expect:
        RatingsSvc.higherRValueCustomers(refCustomer, customers) == [
                new Customer('Samanta Smith', london, 3.7),
                new Customer('Adam Xin', vancouver, 2.110),
                new Customer('Monica Taylor', toronto, 2.110)
        ].asList()

    }
}
