package icaruswings

import grails.plugin.springsecurity.annotation.Secured
import icaruswings.adapters.PayerAdapter

@Secured(['IS_AUTHENTICATED_FULLY'])
class PayerController extends BaseController {

    def payerService
    def customerService

    def index() {
        List<Customer> customerList = customerService.list()

        return [customerList: customerList]
    }

    def save() {
        PayerAdapter payerAdapter = new PayerAdapter((getAuthenticatedUser() as User).customer, params)
        Payer payer = payerService.save(payerAdapter)

        flash.type = "success"
        flash.message = "Cadastro realizado com sucesso."

        redirect(action: "show", id: payer.id)
    }

    def show() {
        Long id = Long.valueOf(params.id)
        Payer payer = payerService.find((getAuthenticatedUser() as User).customerId, id)

        if (!payer) render "Pagador não encontrado"

        return [payer: payer]
    }

    def update() {
        PayerAdapter payerAdapter = new PayerAdapter((getAuthenticatedUser() as User).customer, params)
        payerService.update(payerAdapter)

        flash.type = "success"
        flash.message = "Alterações realizadas com sucesso."

        redirect(action: "show", id: params.id)
    }

    def delete() {
        Long id = Long.valueOf(params.id)

        payerService.delete((getAuthenticatedUser() as User).customerId, id)

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
        Long customerId = Long.valueOf(params.id)
        return [payerList: payerService.list(customerId)]
    }

    def deletedList() {
        Long customerId = Long.valueOf(params.id)
        return [deletedList: payerService.deletedList(customerId)]
    }
}