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
            flash.message = "Cadastro realizado!!"

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

    def list() {
        return [paymentList: paymentService.list()]
    }

    def delete() {
        try {
            Long id = Long.valueOf(params.id)

            paymentService.delete(id)

            flash.type = "success"
            flash.message = "Pagador deletado com sucesso"
        } catch (RuntimeException runtimeException) {
            flash.errors = [runtimeException.getMessage()]
        } catch (Exception exception) {
            flash.errors = ["Erro ao deletar o pagador"]
        } catch (Exception exception) {
            log.error("PaymentController.save >> Erro ao deletar um payment ${params}", exception)

            redirect(action: "index", params: params)
        } finally {
            redirect(action: "index")
        }
    }
}