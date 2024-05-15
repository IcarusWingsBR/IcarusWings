package icaruswings

class CustomerController {

    def customerService

    def index() {}

    def save() {
        try {
            String name = params.name
            String email = params.email
            String cpfCnpj = params.cpfCnpj
            Customer customer = customerService.save(name, email, cpfCnpj)
            redirect(action: "show", id: customer.id)
        } catch (Exception e) {
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