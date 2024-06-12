package icaruswings

import grails.gorm.transactions.Transactional

class BootStrap {

    def init = {
        addRoles()
    }

    @Transactional
    void addRoles() {
        Role.findOrCreateWhere(authority: 'ROLE_ADMIN').save(failOnError: true)
        Role.findOrCreateWhere(authority: 'ROLE_USER').save(failOnError: true)
    }
}
