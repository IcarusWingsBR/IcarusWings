package icaruswings

import grails.plugin.springsecurity.annotation.Secured
import icaruswings.adapters.PayerAdapter

@Secured(['IS_AUTHENTICATED_FULLY'])
class PayerController extends BaseController {

    def payerService

    def index() { }

    def save() {
        PayerAdapter payerAdapter = new PayerAdapter(getCurrentCustomer(), params)
        Payer payer = payerService.save(payerAdapter)

        flash.type = "success"
        flash.message = "Cadastro realizado com sucesso."

        redirect(action: "show", id: payer.id)
    }

    def show() {
        Long id = Long.valueOf(params.id)
        Payer payer = payerService.find(getCurrentCustomerId(), id)

        if (!payer) render "Pagador não encontrado"

        return [payer: payer]
    }

    def update() {
        PayerAdapter payerAdapter = new PayerAdapter(getCurrentCustomer(), params)
        payerService.update(payerAdapter)

        flash.type = "success"
        flash.message = "Alterações realizadas com sucesso."

        redirect(action: "show", id: params.id)
    }

    def delete() {
        Long id = Long.valueOf(params.id)

        payerService.delete(getCurrentCustomerId(), id)

        flash.type = "success"
        flash.message = "Pagador deletado com sucesso"

        redirect(action: "list")
    }

    def restore() {
        Long id = Long.valueOf(params.id)

        payerService.restore(getCurrentCustomerId(), id)

        flash.type = "success"
        flash.message = "Pagador restaurado com sucesso"

        redirect(action: "show", id: params.id)
    }

    def list() {
        String filter = params.payerList

        return [payerList: payerService.list(getCurrentCustomerId(), filter)]
    }
}