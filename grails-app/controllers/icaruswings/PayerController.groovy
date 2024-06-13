package icaruswings

import icaruswings.adapters.PayerAdapter
import icaruswings.repositories.PayerRepository

class PayerController extends BaseController {

    CustomerService customerService
    PayerService payerService

    def index() {
        List<Customer> customerList = customerService.list()

        return [customerList: customerList]
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
        Payer payer = PayerRepository.get(id)
        
        if (!payer) render "Pagador não encontrado"

        return [payer: payer]
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
        return [payerList: payerService.list()]
    }

    def deletedList() {
        return [deletedList: payerService.deletedList()]
    }
}