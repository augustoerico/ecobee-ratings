package io.github.augustoerico

import io.github.augustoerico.models.Rating
import io.github.augustoerico.models.Region
import io.github.augustoerico.services.CustomersSvc
import io.github.augustoerico.services.RatingsSvc
import io.github.augustoerico.services.repositories.CustomersRepository
import spock.lang.Specification

class MainSpec extends Specification {
    def 'should return the ratings'() {
        given:
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

        and:
        def customersSvc = new CustomersSvc(new CustomersRepository())
        def ratingsSvc = new RatingsSvc(customersSvc)

        when:
        def result = Main.run(input, customersSvc, ratingsSvc)

        then:
        result == [
                new Rating('John Doe', new Region('Canada'), 4),
                new Rating('John Doe', new Region('Canada', 'Ontario'), 5),
                new Rating('Alicia Yazzie', new Region('US', 'Arizona'), 10)
        ]
    }
}
