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

                    postalCode: params.postalCode,

                    address: params.address,

                    province: params.province,

                    city: params.city,

                    state: params.state,

                    addressNumber: params.addressNumber,

                    addressComplement: params.addressComplement,

                    personType: params.personType,

                    phone: params.phone
            ]

            Customer customer = customerService.save(parsedParams)

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