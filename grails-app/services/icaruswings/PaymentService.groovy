package icaruswings

import grails.gorm.transactions.Transactional

@Transactional
class PaymentService {
    public Payment save(Map parsedParams) {
        Payment payment = new Payment()

        payment.payer = parsedParams.payer

        payment.paymentType = parsedParams.paymentType

        payment.value = parsedParams.value

        payment.paymentStatus = parsedParams.paymentStatus

        payment.dueDate = parsedParams.dueDate

        payment.save(failOnError: true)

        return payment
    }
}

