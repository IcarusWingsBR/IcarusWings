package icaruswings

class PaymentController {

    def paymentService

    def index() {}

    def save() {
        try {
            Map parsedParams = [
                    payer: params.payer,

                    paymentType: params.paymentType,

                    value: params.value,

                    paymentStatus: params.paymentStatus,

                    dueDate: params.dueDate,
            ]

            Payment payment = paymentService.save(parsedParams)

            redirect(action: "show", id: payment.id)
        } catch (Exception e) {
            redirect(action: "index", params : params)
        }
    }

    def show(){
        try {
            Payment payment = Payment.get(params.id)
            if (!payment) {
                render "Pagamento não encontrado"
            }
            return [payment: payment]
        } catch (Exception e) {
            render "Pagamento não encontrado"
        }
    }
}
