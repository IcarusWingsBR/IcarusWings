package icaruswings

import grails.gorm.transactions.Transactional
import icaruswings.utils.PersonType

@Transactional
class CustomerService {
    public Customer save(Map parsedParams) {
        Customer customer = new Customer()

        customer.name = parsedParams.name

        customer.email = parsedParams.email

        customer.cpfCnpj = parsedParams.cpfCnpj

        customer.personType = PersonType.NATURAL

        customer.save(failOnError: true)

        return customer
    }
}