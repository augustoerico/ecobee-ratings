package io.github.augustoerico.parsers

import spock.lang.Specification

class InputParserSpec extends Specification {
    def 'should parse the input into lines'() {
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

        when:
        def lines = InputParser.parse(input)

        then:
        lines.customersLines == [
                '"John Doe" "Canada/Ontario/Toronto" 1.5',
                '"Samanta Smith" "Canada/Ontario/London" 3.7',
                '"Adam Xin" "Canada/British Columbia/Vancouver" 2.110',
                '"Monica Taylor" "Canada/Ontario/Toronto" 2.110',
                '"Alicia Yazzie" "US/Arizona/Phoenix" 5.532',
                '"Mohammed Zadeh" "Canada/Ontario/Toronto" 1.43'
        ]
        lines.queryLines == [
                '"John Doe" "Canada"',
                '"John Doe" "Canada/Ontario"',
                '"Alicia Yazzie" "US/Arizona"'
        ]
    }
}
