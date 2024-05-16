package icaruswings

import grails.gorm.transactions.Transactional

@Transactional
class PaymentService {
    public Payment save(Map parsedParams) {
        Payment payment = new Payment()

        payment.payer = parsedParams.name

        payment.paymentType = parsedParams.email

        payment.value = parsedParams.cpfCnpj

        payment.paymentStatus = parsedParams.cep

        payment.dueDate = parsedParams.street

        payment.save(failOnError: true)

        return payment
    }
}

