package icaruswings

class CustomerController {

    def customerService

    def index() {}

    def save() {
        try {
            Map parsedParams = [
                    name: params.name,

                    email: params.email,

                    cpfCnpj: params.cpfCnpj,

                    cep: params.cep,

                    street: params.street,

                    neighborhood: params.neighborhood,

                    city: params.city,

                    state: params.state,

                    number: params.number,

                    complement: params.complement,

                    personType: params.personType,
            ]

            Customer customer = customerService.save(params)

            redirect(action: "show", id: customer.id)
        } catch (Exception e) {
            redirect(action: "index", params : params)
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