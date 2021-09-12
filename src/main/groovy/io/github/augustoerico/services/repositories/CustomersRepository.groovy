package io.github.augustoerico.services.repositories

import io.github.augustoerico.models.Customer
import io.github.augustoerico.models.Location
import io.github.augustoerico.models.Query

class CustomersRepository {

    def customersByName = [:]
    def customersByRegion = [:]

    void insert(Customer customer) {
        customersByName = customersByName + [
                (customer.name): customer
        ]

        def indexes = regionIndexes(customer.location)
        if (customersByRegion.containsKey(indexes.byCountry)) {
            customersByRegion.(indexes.byCountry) << customer
        } else {
            customersByRegion.put(indexes.byCountry, [customer])
        }

        if (customersByRegion.containsKey(indexes.byState)) {
            customersByRegion.(indexes.byState) << customer
        } else {
            customersByRegion.put(indexes.byState, [customer])
        }

        if (customersByRegion.containsKey(indexes.byCity)) {
            customersByRegion.(indexes.byCity) << customer
        } else {
            customersByRegion.put(indexes.byCity, [customer])
        }
    }

    Customer fetch(String name) {
        customersByName.(name)
    }

    List<Customer> fetchMany(Query query) {
        customersByRegion.(query.region.toString())
    }

    static regionIndexes(Location location) {
        [
            byCountry: location.country.toLowerCase(),
            byState: "${location.country}/${location.state}".toString().toLowerCase(),
            byCity: "${location.country}/${location.state}/${location.city}".toString().toLowerCase()
        ]
    }

//    static match(Region region, Location location) {
//        if (region.city) {
//            return region.toString() == "${location.country}/${location.state}/${location.city}".toString().toLowerCase()
//        } else if (region.state) {
//            return region.toString() == "${location.country}/${location.state}".toString().toLowerCase()
//        } else {
//            return region.toString() == location.country.toLowerCase()
//        }
//    }
}
