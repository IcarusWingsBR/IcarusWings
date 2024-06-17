package icaruswings

import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class LoginController {

    def auth() { }

    def createCustomer() { }
}
