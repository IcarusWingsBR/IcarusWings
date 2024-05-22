package icaruswings

import icaruswings.utils.adapters.CustomerAdapter

class CustomerController {

    def customerService

    def index() {}

    def save() {
        try {
            CustomerAdapter customerAdapter = new CustomerAdapter(params)
            Customer customer = customerService.save(customerAdapter)

            redirect(action: "show", id: customer.id)
        } catch (Exception e) {
            log.error("Erro ao criar customer", e)

            redirect(action: "index", params: params)
        }
    }

    def show() {
        try {
            Customer customer = Customer.get(params.id)
            if (!customer) {
                render "Cliente não encontrado"
            }
            return [customer: customer]
        } catch (Exception e) {
            render "Cliente não encontrado"
        }
    }

    def update() {
        try {
            CustomerAdapter customerAdapter = new CustomerAdapter(params)
            Customer customer = customerService.update(customerAdapter)

            redirect(action: "show", id: customer.id)
        } catch (Exception e) {
            log.error("Erro ao editar customer", e)

            redirect(action: "index", params: params)
        }
    }
}