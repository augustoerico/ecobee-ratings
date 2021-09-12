package io.github.augustoerico.models

class Rating {
    String name
    Region region
    Integer score

    Rating(String name, Region region, Integer score) {
        this.name = name
        this.region = region
        this.score = score
    }

    @Override
    String toString() {
        "\"${this.name}\" \"${this.region}\" ${this.score}".toString()
    }

    @Override
    boolean equals(Object obj) {
        obj instanceof Rating && (obj.name == this.name && obj.region == this.region && obj.score == this.score)
    }
}
