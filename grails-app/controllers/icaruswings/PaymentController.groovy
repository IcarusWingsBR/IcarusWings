package icaruswings

import grails.plugin.springsecurity.annotation.Secured
import icaruswings.payment.Payment
import icaruswings.adapters.PaymentAdapter
import icaruswings.repositories.PaymentRepository

class PaymentController extends BaseController {

    def paymentService
    def payerService

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def index() {
        List<Payer> payerList = payerService.list()

        return [payerList: payerList]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def save() {
        PaymentAdapter paymentAdapter = new PaymentAdapter(params)
        Payment payment = paymentService.save(paymentAdapter)

        flash.type = "success"
        flash.message = "Cobrança criada com sucesso."

        redirect(action: "show", id: payment.id)
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def show(){
        Long id = Long.valueOf(params.id)
        Payment payment = PaymentRepository.get(id)
        List<Payer> payerList = payerService.list()
        
        if (!payment) render "Cobrança não encontrada."

        return [payment: payment, payerList: payerList]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def update() {
        PaymentAdapter paymentAdapter = new PaymentAdapter(params)
        paymentService.update(paymentAdapter)

        flash.type = "success"
        flash.message = "Alterações realizadas com sucesso."

        redirect(action: "show", id: params.id)
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def list() {
        return [paymentList: paymentService.list()]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def deletedList() {
        return [paymentDeletedList: paymentService.paymentDeletedList()]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def delete() {
        Long id = Long.valueOf(params.id)

        paymentService.delete(id)

        flash.type = "success"
        flash.message = "Cobrança deletada com sucesso"

        redirect(action: "list")
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def restore() {
        Long id = Long.valueOf(params.id)

        paymentService.restore(id)

        flash.type = "success"
        flash.message = "Cobrança restaurada com sucesso"

        redirect(action: "show", id: params.id)
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def confirmPaymentReceived() {
        Long id = Long.valueOf(params.id)

        paymentService.confirmPaymentReceived(id)

        flash.type = "success"
        flash.message = "Status da cobrança atualizado."

        redirect(action: "show", id: params.id)
    }
}