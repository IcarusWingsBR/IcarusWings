package icaruswings

import grails.gorm.transactions.Transactional


@Transactional
class CustomerService {
    public Customer save(String name, String email, String cpfCnpj) {
        Customer customer = new Customer()
        customer.name = name
        customer.email = email
        customer.cpfCnpj = cpfCnpj
        customer.save(failOnError: true)
        return customer

    }
}