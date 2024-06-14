package icaruswings

import grails.plugin.springsecurity.annotation.Secured
import icaruswings.adapters.PayerAdapter

@Secured(['IS_AUTHENTICATED_FULLY'])
class PayerController extends BaseController {

    def payerService
    def customerService

    def index() {
        List<Customer> customerList = customerService.list()

        return [customerList: customerList, customerId: (getAuthenticatedUser() as User).customerId]
    }

    def save() {
        PayerAdapter payerAdapter = new PayerAdapter(params)
        Payer payer = payerService.save(payerAdapter)

        flash.type = "success"
        flash.message = "Cadastro realizado com sucesso."

        redirect(action: "show", id: payer.id)
    }

    def show() {
        Long id = Long.valueOf(params.id)
        Payer payer = Payer.get(id)
        
        if (!payer) render "Pagador não encontrado"

        return [payer: payer, customerId: (getAuthenticatedUser() as User).customerId]
    }

    def update() {
        PayerAdapter payerAdapter = new PayerAdapter(params)
        payerService.update(payerAdapter)

        flash.type = "success"
        flash.message = "Alterações realizadas com sucesso."

        redirect(action: "show", id: params.id)
    }

    def delete() {
        Long id = Long.valueOf(params.id)

        payerService.delete(id)

        flash.type = "success"
        flash.message = "Pagador deletado com sucesso"

        redirect(action: "list")
    }

    def restore() {
        Long id = Long.valueOf(params.id)

        payerService.restore(id)

        flash.type = "success"
        flash.message = "Pagador restaurado com sucesso"

        redirect(action: "show", id: params.id)
    }

    def list() {
        String filter = params.payerList

        if (filter == "deleted") return [payerList: payerService.deletedList()]

        return [payerList: payerService.list(), customerId: (getAuthenticatedUser() as User).customerId]
    }

    def deletedList() {
        return [deletedList: payerService.deletedList()]
    }
}