package icaruswings

import icaruswings.utils.adapters.PaymentAdapter
import icaruswings.utils.repositories.PaymentRepository
import icaruswings.utils.repositories.PayerRepository

class PaymentController extends BaseController {

    def paymentService

    def index() {
        List<Payer> payerList = Payer.list()

        return [payerList: payerList]
    }

    def save() {
        PaymentAdapter paymentAdapter = new PaymentAdapter(params)
        Payment payment = paymentService.save(paymentAdapter)

        flash.type = "success"
        flash.message = "Cobrança criada com sucesso."

        redirect(action: "show", id: payment.id)
    }

    def show(){
        Long id = Long.valueOf(params.id)
        Payment payment = PaymentRepository.get(id)
        List<Payer> payerList = PayerRepository.list()
        
        if (!payment) render "Cobrança não encontrada."

        return [payment: payment, payerList: payerList]
    }

    def update() {
        PaymentAdapter paymentAdapter = new PaymentAdapter(params)
        paymentService.update(paymentAdapter)

        flash.type = "success"
        flash.message = "Alterações realizadas com sucesso."

        redirect(action: "show", id: params.id)
    }

    def list() {
        return [paymentList: paymentService.list()]
    }

    def delete() {
        Long id = Long.valueOf(params.id)

        paymentService.delete(id)

        flash.type = "success"
        flash.message = "Cobrança deletada com sucesso"
    }
}