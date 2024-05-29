package icaruswings

import icaruswings.utils.adapters.PaymentAdapter
import icaruswings.utils.repositories.PaymentRepository

class PaymentController extends BaseController {

    def paymentService

    def index() {}

    def save() {
        PaymentAdapter paymentAdapter = new PaymentAdapter(params)
        Payment payment = paymentService.save(paymentAdapter)

        flash.type = "success"
        flash.message = "Cadastro realizado com sucesso."

        redirect(action: "show", id: payment.id)
    }

    def show(){
        Long id = Long.valueOf(params.id)
        Payment payment = PaymentRepository.get(id)
        
        if (!payment) render "Pagamento não encontrado"

        return [payment: payment]
    }

    def update() {
        PaymentAdapter paymentAdapter = new PaymentAdapter(params)
        paymentService.update(paymentAdapter)

        flash.type = "success"
        flash.message = "Alterações realizadas!!"

        redirect(action: "show", id: params.id)
    }

    def list() {
        return [paymentList: paymentService.list()]
    }
}