package icaruswings

import grails.plugin.springsecurity.annotation.Secured
import icaruswings.adapters.PayerAdapter
import icaruswings.repositories.PayerRepository

class PayerController extends BaseController {

    def payerService
    def customerService

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def index() {
        List<Customer> customerList = customerService.list()

        return [customerList: customerList]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def save() {
        PayerAdapter payerAdapter = new PayerAdapter(params)
        Payer payer = payerService.save(payerAdapter)

        flash.type = "success"
        flash.message = "Cadastro realizado com sucesso."

        redirect(action: "show", id: payer.id)
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def show() {
        Long id = Long.valueOf(params.id)
        Payer payer = PayerRepository.get(id)
        
        if (!payer) render "Pagador não encontrado"

        return [payer: payer]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def update() {
        PayerAdapter payerAdapter = new PayerAdapter(params)
        payerService.update(payerAdapter)

        flash.type = "success"
        flash.message = "Alterações realizadas com sucesso."

        redirect(action: "show", id: params.id)
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def delete() {
        Long id = Long.valueOf(params.id)

        payerService.delete(id)

        flash.type = "success"
        flash.message = "Pagador deletado com sucesso"

        redirect(action: "list")
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def restore() {
        Long id = Long.valueOf(params.id)

        payerService.restore(id)

        flash.type = "success"
        flash.message = "Pagador restaurado com sucesso"

        redirect(action: "show", id: params.id)
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def list() {
        return [payerList: payerService.list()]
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def deletedList() {
        return [deletedList: payerService.deletedList()]
    }
}