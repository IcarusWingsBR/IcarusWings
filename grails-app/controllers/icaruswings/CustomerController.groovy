package icaruswings

import grails.validation.ValidationException
import icaruswings.utils.adapters.CustomerAdapter

class CustomerController {

    def customerService

    def index() {}

    def save() {
        try {
            Customer customer = customerService.save(new CustomerAdapter(params))

            redirect(action: "show", id: customer.id)
        } catch (Exception e) {
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
}