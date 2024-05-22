package icaruswings

import icaruswings.utils.adapters.PayerAdapter

class PayerController {

    def payerService

    def index() {
        List<Customer> customerList = Customer.list()

        return [customerList: customerList]
    }

    def save() {
        try {
            Payer payer = payerService.save(new PayerAdapter(params))

            redirect(action: "show", id: payer.id)
        } catch (Exception e) {
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

    def delete() {
        try {
            Long id = Long.parseLong(params.id)

            payerService.delete(id)
        } catch (RuntimeException runtimeException) {
            flash.errors = [runtimeException.getMessage()]
        } catch (Exception e) {
            flash.errors = ["Erro ao deletar o pagador"]
        }
        flash.message = "Pagador deletado com sucesso"
        redirect(action: "index")
    }
}