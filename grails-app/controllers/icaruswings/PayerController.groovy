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
            PayerAdapter payerAdapter = new PayerAdapter(params)
            Payer payer = payerService.save(payerAdapter)

            flash.type = "success"
            flash.message = "Cadastro realizado com sucesso."

            redirect(action: "show", id: payer.id)
        } catch (Exception exception) {
            log.error("PayerController.save >> Erro ao criar payer ${params}", exception)

            flash.type = "error"
            flash.message = exception

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
        } catch (Exception exception) {
            render "Pagador não encontrado"
        }
    }

    def delete() {
        try {
            Long id = Long.valueOf(params.id)

            payerService.delete(id)

            flash.type = "success"
            flash.message = "Pagador deletado com sucesso"
        } catch (RuntimeException runtimeException) {
            flash.errors = [runtimeException.getMessage()]
        } catch (Exception exception) {
            log.error("PayerController.save >> Erro ao atualizar um payer ${params}", exception)

            flash.errors = ["Erro ao deletar o pagador"]

            redirect(action: "index", params: params)
        } finally {
            redirect(action: "index")
        }
    }

    def list() {
        return [payerList: payerService.list()]
    }
}