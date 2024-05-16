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

        customer.cep = parsedParams.cep

        customer.street = parsedParams.street

        customer.neighborhood = parsedParams.neighborhood

        customer.city = parsedParams.city

        customer.state = parsedParams.state

        customer.number = parsedParams.number

        customer.complement = parsedParams.complement
        
        customer.personType = PersonType.NATURAL

        customer.save(failOnError: true)

        return customer
    }
}