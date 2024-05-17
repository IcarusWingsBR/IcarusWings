package icaruswings

import grails.gorm.transactions.Transactional
import icaruswings.utils.PaymentStatus

@Transactional
class PaymentService {
    public Payment save(Map parsedParams) {
        Payment payment = new Payment()

        payment.payer = parsedParams.payer

        payment.paymentType = parsedParams.paymentType

        payment.value = parsedParams.value

        payment.paymentStatus = PaymentStatus.WAITING_PAYMENT

        payment.dueDate = parsedParams.dueDate

        payment.save(failOnError: true)

        return payment
    }
}

