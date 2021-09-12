package io.github.augustoerico.models

class Location {
    String country
    String state
    String city

    Location(String country, String state, String city) {
        this.country = country
        this.state = state
        this.city = city
    }

    @Override
    boolean equals(Object obj) {
        obj instanceof Location && obj.country == this.country && obj.state == this.state && obj.city == this.city
    }
}
