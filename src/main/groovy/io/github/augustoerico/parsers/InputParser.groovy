package io.github.augustoerico.parsers

class InputParser {
    static parse(String input) {
        def lines = input.split('\n')
        def separatorIndex= lines.findIndexOf {it.size() == 0 }
        def customerLines = lines[0..separatorIndex - 1]
        def queryLines = lines[separatorIndex + 1..-1]
        [
                customersLines: customerLines,
                queryLines: queryLines
        ]
    }
}
