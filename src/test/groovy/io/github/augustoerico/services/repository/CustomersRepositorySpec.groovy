package io.github.augustoerico.services.repository

import io.github.augustoerico.models.Customer
import io.github.augustoerico.models.Location
import io.github.augustoerico.models.Query
import io.github.augustoerico.models.Region
import io.github.augustoerico.services.repositories.CustomersRepository
import spock.lang.Specification

class CustomersRepositorySpec extends Specification {

    def toronto = new Location('Canada', 'Ontario', 'Toronto')
    def london = new Location('Canada', 'Ontario', 'London')
    def vancouver = new Location('Canada', 'British Columbia', 'Vancouver')
    def phoenix = new Location('US', 'Arizona', 'Phoenix')

    def 'should insert a customer'() {
        given:
        def repository = new CustomersRepository()
        def customer = new Customer('John Doe', toronto, 1.23)

        when:
        repository.insert(customer)

        then:
        repository.customersByName == ['John Doe': customer]
        repository.customersByRegion == [
                canada: [customer],
                'canada/ontario': [customer],
                'canada/ontario/toronto': [customer]
        ]
    }

    def 'should insert many customers'() {
        given:
        def repository = new CustomersRepository()

        and:
        def customer1 = new Customer('John Doe', toronto, 1.23)
        def customer2 = new Customer('Samantha Smith', london, 2.34)
        def customer3 = new Customer('Adam Xin', vancouver, 2.110)
        def customer4 = new Customer('Monica Taylor', toronto, 2.110)
        def customer5 = new Customer('Mohammed Zadeth', toronto, 1.43)
        def customer6 = new Customer('Alicia Yazzie', phoenix, 5.532)
        def customers = [
                customer1, customer2, customer3, customer4, customer5, customer6
        ]

        when:
        customers.collect {repository.insert(it)}

        then:
        repository.customersByName == [
                'John Doe': customer1,
                'Samantha Smith': customer2,
                'Adam Xin': customer3,
                'Monica Taylor': customer4,
                'Mohammed Zadeth': customer5,
                'Alicia Yazzie': customer6
        ]
        repository.customersByRegion == [
                canada: [customer1, customer2, customer3, customer4, customer5],
                us: [customer6],
                'canada/ontario': [customer1, customer2, customer4, customer5],
                'canada/british columbia': [customer3],
                'us/arizona': [customer6],
                'canada/ontario/toronto': [customer1, customer4, customer5],
                'canada/ontario/london': [customer2],
                'canada/british columbia/vancouver': [customer3],
                'us/arizona/phoenix': [customer6]
        ]
    }

    def 'should fetch a customer'() {
        given:
        def repository = new CustomersRepository()

        and:
        def customer = new Customer('Wile Coyote', phoenix, 1.23)
        repository.customersByName = [
                'Wile Coyote': customer
        ]

        when:
        def fetchedCustomer = repository.fetch(customer.name)

        then:
        fetchedCustomer == customer
    }

    def 'should fetch many customers'() {
        given:
        def customer1 = new Customer('John Doe', toronto, 1.23)
        def customer2 = new Customer('Samantha Smith', london, 2.34)
        def customer3 = new Customer('Adam Xin', vancouver, 2.110)

        and:
        def repository = new CustomersRepository()
        repository.customersByRegion = [
                'canada': [customer1, customer2, customer3],
                'canada/ontario': [customer1, customer2],
                'canada/british columbia': [customer3],
                'canada/ontario/toronto': [customer1],
                'canada/ontario/london': [customer2],
                'canada/british columbia/vancouver': [customer3]
        ]

        and:
        def queryOntario = new Query(
                'Wile Coyote',
                new Region('Canada', 'Ontario')
        )
        def queryVancouver = new Query(
                'Roadrunner',
                new Region('Canada', 'British Columbia', 'Vancouver')
        )

        when:
        def customersOntario = repository.fetchMany(queryOntario)
        def customersVancouver = repository.fetchMany(queryVancouver)

        then:
        customersOntario == [
                customer1, customer2
        ]
        customersVancouver == [
                customer3
        ]
    }
}
