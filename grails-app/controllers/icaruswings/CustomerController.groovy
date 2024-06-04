package icaruswings

import icaruswings.adapters.CustomerAdapter
import icaruswings.repositories.CustomerRepository

class CustomerController extends BaseController {

    def customerService

    def index() {}

    def save() {
        CustomerAdapter customerAdapter = new CustomerAdapter(params)
        Customer customer = customerService.save(customerAdapter)

        flash.type = "success"
        flash.message = "Cadastro realizado com sucesso."

        redirect(action: "show", id: customer.id)
    }

    def show() {
        Long id = Long.valueOf(params.id)
        Customer customer = CustomerRepository.get(id)

        if (!customer) render "Cliente não encontrado"

        return [customer: customer]
    }

    def update() {
        CustomerAdapter customerAdapter = new CustomerAdapter(params)
        customerService.update(customerAdapter)

        flash.type = "success"
        flash.message = "Alterações realizadas com sucesso."

        redirect(action: "show", id: params.id)
    }

    def list() {
        return [customerList: customerService.list()]
    }
}