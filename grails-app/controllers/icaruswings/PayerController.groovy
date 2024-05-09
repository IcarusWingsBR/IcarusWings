package icaruswings


class PayerController {

    def payerService

    def index() {
        List<Customer> customerList = Customer.list()
        return [customerList: customerList]
    }

    def save() {
        try {
            Map parsedParams = [
                name : params.name,
                email : params.email,
                cpfCnpj : params.cpfCnpj,
                customerId : params.long("customerId")
            ]
            Payer payer = payerService.save(parsedParams)
            redirect(action: "show", id: payer.id)
        } catch (Exception e) {
            println(e)
            redirect(action: "index", params: params)
        }
    }
    def show() {
        try {
            Payer payer = Payer.get(params.id)
            if (!payer) {
                render "Pagador não encontrado"
            }
            return [payer: payer]
        } catch (Exception e) {
            render "Pagador não encontrado"
        }
    }



}
