package icaruswings

import icaruswings.payment.Payment
import icaruswings.adapters.PaymentAdapter
import icaruswings.repositories.PaymentRepository

class PaymentController extends BaseController {

    def paymentService
    def payerService

    static allowedMethods = [index: "GET", save: "POST", show: "GET", update: "POST", list: "GET", deletedList: "GET", delete: ["POST", "DELETE"], restore: "POST", confirmPaymentReceived: "POST"]

    def index() {
        List<Payer> payerList = payerService.list()

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
        List<Payer> payerList = payerService.list()
        
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

        redirect(action: "show", id: payment.id)
    }

    def confirmPaymentReceived() {
        Long id = Long.valueOf(params.id)

        paymentService.confirmPaymentReceived(id)

        flash.type = "success"
        flash.message = "Status da cobrança atualizado."

        redirect(action: "show", id: payment.id)
    }
}