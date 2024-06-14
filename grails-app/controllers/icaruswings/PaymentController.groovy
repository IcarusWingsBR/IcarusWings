package icaruswings

import grails.plugin.springsecurity.annotation.Secured
import icaruswings.payment.Payment
import icaruswings.adapters.PaymentAdapter

@Secured(['IS_AUTHENTICATED_FULLY'])
class PaymentController extends BaseController {

    def paymentService
    def payerService

    def index() {
        String filter = "active"
        List<Payer> payerList = payerService.list(getCurrentCustomerId(), filter)

        return [payerList: payerList]
    }

    def save() {
        PaymentAdapter paymentAdapter = new PaymentAdapter((getAuthenticatedUser() as User).customer, params)
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
        PaymentAdapter paymentAdapter = new PaymentAdapter((getAuthenticatedUser() as User).customer, params)
        paymentService.update(paymentAdapter)

        flash.type = "success"
        flash.message = "Alterações realizadas com sucesso."

        redirect(action: "show", id: params.id)
    }

    def list() {
        String filter = params.paymentList

        if (filter == "deleted") return [
                paymentList: paymentService.deletedList((getAuthenticatedUser() as User).customerId)
        ]

        return [paymentList: paymentService.list((getAuthenticatedUser() as User).customerId)]
    }

    def delete() {
        Long id = Long.valueOf(params.id)

        paymentService.delete(((getAuthenticatedUser() as User).customerId), id)

        flash.type = "success"
        flash.message = "Cobrança deletada com sucesso"

        redirect(action: "list")
    }

    def restore() {
        Long id = Long.valueOf(params.id)

        paymentService.restore(((getAuthenticatedUser() as User).customerId), id)

        flash.type = "success"
        flash.message = "Cobrança restaurada com sucesso"

        redirect(action: "show", id: params.id)
    }

    def confirmPaymentReceived() {
        Long id = Long.valueOf(params.id)

        paymentService.confirmPaymentReceived(((getAuthenticatedUser() as User).customerId), id)

        flash.type = "success"
        flash.message = "Status da cobrança atualizado."

        redirect(action: "show", id: params.id)
    }
}