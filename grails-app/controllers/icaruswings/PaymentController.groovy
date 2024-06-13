package icaruswings

import icaruswings.payment.Payment
import icaruswings.adapters.PaymentAdapter
import icaruswings.repositories.PaymentRepository

class PaymentController extends BaseController {

    NotificationService notificationService
    PayerService payerService
    PaymentService paymentService

    def index() {
        List<Payer> payerList = payerService.list()

        return [payerList: payerList, notifications: notificationService.list((getAuthenticatedUser() as User).customerId)]
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
        List<Payer> payerList = payerService.list()
        
        if (!payment) render "Cobrança não encontrada."

        return [payment: payment, payerList: payerList, notifications: notificationService.list((getAuthenticatedUser() as User).customerId)]
    }

    def update() {
        PaymentAdapter paymentAdapter = new PaymentAdapter(params)
        paymentService.update(paymentAdapter)

        flash.type = "success"
        flash.message = "Alterações realizadas com sucesso."

        redirect(action: "show", id: params.id)
    }

    def list() {
        return [paymentList: paymentService.list(), notifications: notificationService.list((getAuthenticatedUser() as User).customerId)]
    }

    def deletedList() {
        return [paymentDeletedList: paymentService.paymentDeletedList()]
    }

    def delete() {
        Long id = Long.valueOf(params.id)

        paymentService.delete(id)

        flash.type = "success"
        flash.message = "Cobrança deletada com sucesso"

        redirect(action: "list")
    }

    def restore() {
        Long id = Long.valueOf(params.id)

        paymentService.restore(id)

        flash.type = "success"
        flash.message = "Cobrança restaurada com sucesso"

        redirect(action: "show", id: params.id)
    }

    def confirmPaymentReceived() {
        Long id = Long.valueOf(params.id)

        paymentService.confirmPaymentReceived(id)

        flash.type = "success"
        flash.message = "Status da cobrança atualizado."

        redirect(action: "show", id: params.id)
    }
}