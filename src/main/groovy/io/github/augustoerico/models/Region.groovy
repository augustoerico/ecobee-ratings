package io.github.augustoerico.models

class Region {
    String country
    String state
    String city

    Region(String country) {
        this.country = country
    }

    Region(String country, String state) {
        this.country = country
        this.state = state
    }

    Region(String country, String state, String city) {
        this.country = country
        this.state = state
        this.city = city
    }

    @Override
    String toString() {
        if (this.city) {
            return "${this.country}/${this.state}/${this.city}".toString().toLowerCase()
        } else if (this.state) {
            return "${this.country}/${this.state}".toString().toLowerCase()
        } else {
            return this.country.toLowerCase()
        }
    }

    @Override
    boolean equals(Object obj) {
        obj instanceof Region && obj.country == this.country && obj.state == this.state && obj.city == this.city
    }
}
