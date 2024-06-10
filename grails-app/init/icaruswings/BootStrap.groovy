package icaruswings

import grails.gorm.transactions.Transactional
import icaruswings.utils.PersonType

class BootStrap {

    def init = {
        addTestUserWithCustomer()
    }

    @Transactional
    void addTestUserWithCustomer() {
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(failOnError: true)

        def customer = new Customer(
                name: 'Test Customer',
                email: 'test.customer@example.com',
                cpfCnpj: '12345678901',
                postalCode: '12345-678',
                address: '123 Main St',
                province: 'Downtown',
                city: 'Test City',
                state: 'TS',
                addressNumber: 123,
                addressComplement: 'Apt 4B',
                phone: '123-456-7890',
                personType: PersonType.NATURAL
        ).save(failOnError: true)

        def testUser = new User(username: 'user1', password: 'password', customer: customer).save(failOnError: true)

        UserRole.create(testUser, adminRole, true)

        UserRole.withSession {
            it.flush()
            it.clear()
        }
    }
}
