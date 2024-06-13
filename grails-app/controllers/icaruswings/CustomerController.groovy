package icaruswings

import grails.plugin.springsecurity.annotation.Secured
import icaruswings.adapters.CustomerAdapter
import icaruswings.adapters.UserAdapter
import icaruswings.repositories.CustomerRepository

class CustomerController extends BaseController {

    def customerService

    @Secured(['permitAll'])
    def index() {}

    @Secured(['permitAll'])
    def save() {
        CustomerAdapter customerAdapter = new CustomerAdapter(params)
        UserAdapter userAdapter = new UserAdapter(params)
        customerService.save(customerAdapter, userAdapter)

        flash.type = "success"
        flash.message = "Cadastro realizado com sucesso."

        redirect(action: "auth", controller: "login")
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def show() {
        Long id = Long.valueOf(params.id)
        Customer customer = CustomerRepository.get(id)

        if (!customer) render "Cliente não encontrado"

        return [customer: customer]
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
        return [customerList: customerService.list()]
    }
}