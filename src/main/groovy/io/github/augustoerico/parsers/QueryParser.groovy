package io.github.augustoerico.parsers

import io.github.augustoerico.models.Query
import io.github.augustoerico.models.Region

class QueryParser {

    static toQuery(String line) {
        def sLine = line.replaceAll(/\s+/, ' ')
        def pattern = ~/"(?<name>.+)"\s+"(?<region>.+)"/
        def matcher = sLine =~ pattern
        if (!matcher.matches()) {
            throw new Exception("Invalid `query` line:\n${line}")
        }
        def name = matcher.group('name')
        def region = toRegion(matcher.group('region'))
        new Query(name, region)
    }

    static toRegion(String line) {
        def components = line.split('/')
        switch (components.size()) {
            case 1:
                return new Region(components[0])
            case 2:
                return new Region(components[0], components[1])
            case 3:
                return new Region(components[0], components[1], components[2])
        }
    }
}
