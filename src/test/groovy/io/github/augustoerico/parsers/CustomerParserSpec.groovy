package io.github.augustoerico.parsers

import io.github.augustoerico.models.Customer
import io.github.augustoerico.models.Location
import spock.lang.Specification

class CustomerParserSpec extends Specification {

    def 'should parse a line into customer'() {
        expect:
        CustomerParser.toCustomer(line) == refCustomer

        where:
        line                                                                | refCustomer
        '"John Doe" "Canada/Ontario/Toronto" 1.23'                          | new Customer('John Doe', new Location('Canada', 'Ontario', 'Toronto'), 1.23)
        '"Alan"  "Dominican Republic/Barahona/Santa Cruz de Barahona" 4.56' | new Customer('Alan', new Location('Dominican Republic', 'Barahona', 'Santa Cruz de Barahona'), 4.56)
        '"Terry" "US/Arizona/Phoenix" 5'                                    | new Customer('Terry', new Location('US', 'Arizona', 'Phoenix'), 5.0)
    }
}
