package icaruswings

import icaruswings.utils.adapters.PaymentAdapter

class PaymentController {

    def paymentService

    def index() {}

    def save() {
        try {
            PaymentAdapter paymentAdapter = new PaymentAdapter(params)
            Payment payment = paymentService.save(paymentAdapter)

            flash.type = "success"
            flash.message = "Cadastro realizado com sucesso."

            redirect(action: "show", id: payment.id)
        } catch (Exception exception) {
            log.error("PaymentController.save >> Erro ao criar payment ${params}", exception)
            
            flash.type = "error"
            flash.message = exception

            redirect(action: "index", params: params)
        }
    }

    def show(){
        try {
            Payment payment = Payment.get(params.id)
            
            if (!payment) {
                render "Pagamento não encontrado"
            }

            return [payment: payment]
        } catch (Exception exception) {
            render "Pagamento não encontrado"
        }
    }

    def update() {
        try {
            PaymentAdapter paymentAdapter = new PaymentAdapter(params)
            paymentService.update(paymentAdapter)

            flash.type = "success"
            flash.message = "Alterações realizadas!!"

            redirect(action: "show", id: params.id)
        } catch (Exception exception) {
            log.error("PaymentController.update >> Erro ao atualizar pagador", exception)

            flash.type = "error"
            flash.message = exception

            redirect(action: "index", params: params)
        }
    }

    def list() {
        return [paymentList: paymentService.list()]
    }
}