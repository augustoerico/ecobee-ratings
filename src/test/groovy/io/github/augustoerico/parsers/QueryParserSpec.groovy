package io.github.augustoerico.parsers

import io.github.augustoerico.models.Query
import io.github.augustoerico.models.Region
import spock.lang.Specification

class QueryParserSpec extends Specification {

    def 'should parse a line into query'() {
        expect:
        QueryParser.toQuery(line) == refQuery

        where:
        line                                    | refQuery
        '"John Doe" "Canada/Ontario/Toronto"'   | new Query('John Doe', new Region('Canada', 'Ontario', 'Toronto'))
        '"Alan"  "Dominican Republic/Barahona"' | new Query('Alan', new Region('Dominican Republic', 'Barahona'))
        '"Terry" "US"'                          | new Query('Terry', new Region('US'))
    }
}
