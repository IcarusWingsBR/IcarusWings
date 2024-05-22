package icaruswings

import icaruswings.utils.adapters.PayerAdapter

class PayerController {

    def payerService

    def index() {
        List<Customer> customerList = Customer.list()

        return [customerList: customerList]
    }

    def save() {
        try {
            PayerAdapter payerAdapter = new PayerAdapter(params)
            Payer payer = payerService.save(payerAdapter)

            redirect(action: "show", id: payer.id)
        } catch (Exception e) {
            log.error("Erro ao salvar pagador", e)

            redirect(action: "index", params: params)
        }
    }

    def show() {
        try {
            Payer payer = Payer.get(params.id)
            if (!payer) {
                render "Pagador não encontrado"
            }

            return [payer: payer]
        } catch (Exception e) {
            render "Pagador não encontrado"
        }
    }

    def update() {
        try {
            Payer payer = payerService.update(new PayerAdapter(params))

            redirect(action: "show", id: payer.id)
        } catch (Exception e) {
            log.error("Erro no update", e)

            redirect(action: "index", params: params)
        }
    }
}