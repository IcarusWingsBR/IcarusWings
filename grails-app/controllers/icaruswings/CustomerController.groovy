package icaruswings

import icaruswings.utils.adapters.CustomerAdapter

class CustomerController {

    def customerService

    def index() {}

    def save() {
        try {
            CustomerAdapter customerAdapter = new CustomerAdapter(params)
            Customer customer = customerService.save(customerAdapter)

            flash.type = "success"
            flash.message = "Cadastro realizado!!"

            redirect(action: "show", id: customer.id)
        } catch (Exception exception) {
            log.error("CustomerController.save >> Erro ao criar customer ${params}", exception)
            
            flash.type = "error"
            flash.message = exception

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
            customerService.update(customerAdapter)

            flash.type = "success"
            flash.message = "Alterações realizadas!!"

            redirect(action: "index")
        } catch (Exception exception) {
            log.error("CustomerController.update >> Erro ao editar customer ${params}", exception)

            flash.type = "error"
            flash.message = exception

            redirect(action: "index", params: params)
        }
    }
}