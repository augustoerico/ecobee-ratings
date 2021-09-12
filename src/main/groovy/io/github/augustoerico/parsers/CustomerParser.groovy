package io.github.augustoerico.parsers

import io.github.augustoerico.models.Customer
import io.github.augustoerico.models.Location

class CustomerParser {
    static toCustomer(String line) {
        def sLine = line.replaceAll(/\s+/, ' ')
        def pattern = ~/"(?<name>.+)"\s+"(?<location>.+)"\s+(?<rValue>\d+(\.\d+)?)/
        def matcher = sLine =~ pattern
        if (!matcher.matches()) {
            throw new Exception("Invalid `customer` line:\n${line}")
        }
        def name = matcher.group('name')
        def location = toLocation(matcher.group('location'))
        def rValue = Float.valueOf(matcher.group('rValue'))
        new Customer(name, location, rValue)
    }

    static toLocation(String line) {
        def pattern = ~/(?<country>\w+(\s+\w*)*)\/(?<state>\w+(\s+\w*)*)\/(?<city>\w+(\s+\w*)*)/
        def matcher = line =~ pattern
        if (!matcher.matches()) {
            throw new Exception("Invalid `location` line:\n${line}")
        }
        def country = matcher.group('country')
        def state = matcher.group('state')
        def city = matcher.group('city')
        new Location(country, state, city)
    }
}
