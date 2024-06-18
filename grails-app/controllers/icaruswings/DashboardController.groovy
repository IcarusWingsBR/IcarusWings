package icaruswings

import grails.plugin.springsecurity.annotation.Secured
import icaruswings.repositories.CustomerRepository

class DashboardController extends BaseController {

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def index() {
        Long id = getCurrentCustomerId()

        Customer customer = CustomerRepository.get(id)

        return [customer: customer]
    }
}