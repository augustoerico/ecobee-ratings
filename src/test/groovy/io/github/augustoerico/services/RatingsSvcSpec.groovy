package io.github.augustoerico.services

import io.github.augustoerico.models.Customer
import io.github.augustoerico.models.Location
import io.github.augustoerico.models.Query
import io.github.augustoerico.models.Rating
import io.github.augustoerico.models.Region
import spock.lang.Specification

class RatingsSvcSpec extends Specification {

    def 'should return the ratings for a single query'() {
        given:
        CustomersSvc customersSvc = Stub()

        and:
        Location toronto = new Location('Canada', 'Ontario', 'Toronto')
        Location london = new Location('Canada', 'Ontario', 'London')
        Location vancouver = new Location('Canada', 'British Columbia', 'Vancouver')

        and:
        // query := 'John Doe', 'Canada'
        customersSvc.readMany(_ as Query) >> [
                new Customer('John Doe', toronto, 1.5),
                new Customer('Samanta Smith', london, 3.7),
                new Customer('Adam Xin', vancouver, 2.110),
                new Customer('Monica Taylor', toronto, 2.110),
                new Customer('Mohammed Zadeh', toronto, 1.43)
        ]
        // name := 'John Doe'
        customersSvc.read(_ as String) >> new Customer('John Doe', toronto, 1.5)

        and:
        Query query = new Query('John Doe', new Region('Canada'))

        and:
        def ratingsSvc = new RatingsSvc(customersSvc)

        when:
        def rating = ratingsSvc.readMany(query)

        then:
        rating == new Rating('John Doe', new Region('Canada'), 4)
    }

    def 'should return the ratings for a single query 2'() {
        given:
        CustomersSvc customersSvc = Stub()

        and:
        Location toronto = new Location('Canada', 'Ontario', 'Toronto')
        Location london = new Location('Canada', 'Ontario', 'London')

        and:
        // query := 'John Doe', 'Canada/Ontario'
        customersSvc.readMany(_ as Query) >> [
                new Customer('John Doe', toronto, 1.5),
                new Customer('Samanta Smith', london, 3.7),
                new Customer('Monica Taylor', toronto, 2.110),
                new Customer('Mohammed Zadeh', toronto, 1.43)
        ]
        // name := 'John Doe'
        customersSvc.read(_ as String) >> new Customer('John Doe', toronto, 1.5)

        and:
        Query query = new Query('John Doe', new Region('Canada', 'Ontario'))

        and:
        def ratingsSvc = new RatingsSvc(customersSvc)

        when:
        def rating = ratingsSvc.readMany(query)

        then:
        rating == new Rating('John Doe', new Region('Canada', 'Ontario'), 5)
    }

    def 'should return the ratings for a single query 3'() {
        given:
        CustomersSvc customersSvc = Stub()

        and:
        Location phoenix = new Location('US', 'Arizona', 'Phoenix')

        and:
        // query := 'Alicia Yazzie', 'US/Arizona'
        customersSvc.readMany(_ as Query) >> [
                new Customer('Alicia Yazzie', phoenix, 5.532)
        ]
        // name := 'John Doe'
        customersSvc.read(_ as String) >> new Customer('Alicia Yazzie', phoenix, 5.532)

        and:
        Query query = new Query('Alicia Yazzie', new Region('US', 'Arizona'))

        and:
        def ratingsSvc = new RatingsSvc(customersSvc)

        when:
        def rating = ratingsSvc.readMany(query)

        then:
        rating == new Rating('Alicia Yazzie', new Region('US', 'Arizona'), 10)
    }
}
