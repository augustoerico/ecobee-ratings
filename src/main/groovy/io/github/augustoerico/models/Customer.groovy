package io.github.augustoerico.models

import io.github.augustoerico.parsers.CustomerParser

class Customer {
    String name
    Location location
    Float rValue

    Customer(String name, Location location, Float rValue) {
        this.name = name
        this.location = location
        this.rValue = rValue
    }

    @Override
    boolean equals(Object obj) {
        obj instanceof Customer && (obj.name == this.name && obj.location == this.location && obj.rValue.floatValue() == this.rValue.floatValue())
    }
}
