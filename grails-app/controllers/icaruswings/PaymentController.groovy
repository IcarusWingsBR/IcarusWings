package icaruswings

import grails.plugin.springsecurity.annotation.Secured
import icaruswings.payment.Payment
import icaruswings.adapters.PaymentAdapter

@Secured(['IS_AUTHENTICATED_FULLY'])
class PaymentController extends BaseController {

    PayerService payerService
    PaymentService paymentService

    def index() {
        String filter = params.payerList
        List<Payer> payerList = payerService.list(getCurrentCustomerId(), filter)

        return [payerList: payerList]
    }

    def save() {
        PaymentAdapter paymentAdapter = new PaymentAdapter(getCurrentCustomer(), params)
        Payment payment = paymentService.save(paymentAdapter)

        flash.type = "success"
        flash.message = "Cobrança criada com sucesso."

        redirect(action: "show", id: payment.id)
    }

    def show(){
        Long id = Long.valueOf(params.id)
        Payment payment = Payment.get(id)
        String filter = "active"
        List<Payer> payerList = payerService.list(getCurrentCustomerId(), filter)
        
        if (!payment) render "Cobrança não encontrada."

        return [payment: payment, payerList: payerList]
    }

    def update() {
        PaymentAdapter paymentAdapter = new PaymentAdapter(getCurrentCustomer(), params)
        paymentService.update(paymentAdapter)

        flash.type = "success"
        flash.message = "Alterações realizadas com sucesso."

        redirect(action: "show", id: params.id)
    }

    def list() {
        String filter = params.paymentList

        return [paymentList: paymentService.list(getCurrentCustomerId(), filter)]
    }

    def delete() {
        Long id = Long.valueOf(params.id)

        paymentService.delete(getCurrentCustomerId(), id)

        flash.type = "success"
        flash.message = "Cobrança deletada com sucesso"

        redirect(action: "list")
    }

    def restore() {
        Long id = Long.valueOf(params.id)

        paymentService.restore(getCurrentCustomerId(), id)

        flash.type = "success"
        flash.message = "Cobrança restaurada com sucesso"

        redirect(action: "show", id: params.id)
    }

    def confirmPaymentReceived() {
        Long id = Long.valueOf(params.id)

        paymentService.confirmPaymentReceived(getCurrentCustomerId(), id)

        flash.type = "success"
        flash.message = "Status da cobrança atualizado."

        redirect(action: "show", id: params.id)
    }
}