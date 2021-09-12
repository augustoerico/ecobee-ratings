package io.github.augustoerico.models

import io.github.augustoerico.parsers.QueryParser

class Query {
    String name
    Region region

    Query(String name, Region region) {
        this.name = name
        this.region = region
    }

    @Override
    boolean equals(Object obj) {
        obj instanceof Query && (obj.name == this.name && obj.region == this.region)
    }
}
