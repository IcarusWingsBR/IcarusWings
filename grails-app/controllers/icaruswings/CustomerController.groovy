package icaruswings

import grails.plugin.springsecurity.annotation.Secured
import icaruswings.adapters.CustomerAdapter
import icaruswings.repositories.CustomerRepository

class CustomerController extends BaseController {

    CustomerService customerService
    NotificationService notificationService

    @Secured(['permitAll'])
    def index() {}
    
    def save() {
        CustomerAdapter customerAdapter = new CustomerAdapter(params)
        Customer customer = customerService.save(customerAdapter)

        flash.type = "success"
        flash.message = "Cadastro realizado com sucesso."

        redirect(action: "show", id: customer.id)
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def show() {
        Long id = Long.valueOf(params.id)
        Customer customer = CustomerRepository.get(id)

        if (!customer) render "Cliente não encontrado"

        return [customer: customer, notifications: notificationService.list((getAuthenticatedUser() as User).customerId)]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def update() {
        CustomerAdapter customerAdapter = new CustomerAdapter(params)
        customerService.update(customerAdapter)

        flash.type = "success"
        flash.message = "Alterações realizadas com sucesso."

        redirect(action: "show", id: params.id)
    }

    @Secured(['ROLE_ADMIN'])
    def list() {
        return [customerList: customerService.list(), notifications: notificationService.list((getAuthenticatedUser() as User).customerId)]
    }
}