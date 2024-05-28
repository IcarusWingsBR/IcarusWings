package icaruswings

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import icaruswings.utils.adapters.PaymentAdapter
import icaruswings.utils.date.DateUtils

@Transactional
class PaymentService {

    public Payment save(PaymentAdapter paymentAdapter) {
        Payment validatedPayment= validateSave(paymentAdapter)

        if (validatedPayment.hasErrors()) {
            throw new ValidationException("Não foi possível salvar a cobrança", validatedPayment.errors)
        }

        Payment payment = new Payment()

        payment.payer = paymentAdapter.payer
        payment.paymentType = paymentAdapter .paymentType
        payment.paymentStatus = paymentAdapter.paymentStatus
        payment.value = paymentAdapter.value
        payment.dueDate = paymentAdapter.dueDate

        payment.save(failOnError: true)

        return payment
    }

    private Payment validateSave(PaymentAdapter paymentAdapter) {
        Payment payment = new Payment()

        if (!paymentAdapter.paymentType) {
            payment.errors.rejectValue("paymentType", null, "O tipo de pagamento é inválido")
        }

        if (!paymentAdapter.value) {
            payment.errors.rejectValue("value", null, "O campo valor é obrigatório")
        } else if (paymentAdapter.value < 0) {
            payment.errors.rejectValue("value", null, "O valor informado deve ser positivo")
        }

        if (!paymentAdapter.dueDate) {
            payment.errors.rejectValue("dueDate",  null, "O campo data de vencimento é obrigatório")
        } else if (!DateUtils.isBeforeToday(paymentAdapter.dueDate)) {
            payment.errors.rejectValue("dueDate",  null, "A data informada é inválida")
        }

        return payment
    }
}
