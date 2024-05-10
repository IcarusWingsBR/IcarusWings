package icaruswings

class CustomerController {
    def index() {
    }
    def customerService

    def save() {
        try {
            Customer customer = customerService.save(params)
            redirect(action: "show", id: customer.id)
        } catch (Exception e) {
            println(e)
            render "Não foi possível salvar"
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