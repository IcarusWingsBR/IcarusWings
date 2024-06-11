package icaruswings

import grails.gorm.transactions.Transactional

class BootStrap {

    def init = {
        addTestUserWithCustomer()
    }

    @Transactional
    void addTestUserWithCustomer() {
        Role.findOrCreateWhere(authority: 'ROLE_ADMIN').save(failOnError: true)
    }
}
