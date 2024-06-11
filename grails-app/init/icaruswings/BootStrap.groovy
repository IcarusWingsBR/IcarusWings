package icaruswings

import grails.gorm.transactions.Transactional
import icaruswings.utils.PersonType

class BootStrap {

    def init = {
        addTestUserWithCustomer()
    }

    @Transactional
    void addTestUserWithCustomer() {
        Role.findOrCreateWhere(authority: 'ROLE_ADMIN').save(failOnError: true)
        Role.findOrCreateWhere(authority: 'ROLE_USER').save(failOnError: true)
    }
}
