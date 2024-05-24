package icaruswings

import grails.gorm.transactions.Transactional
import icaruswings.utils.PaymentStatus
import icaruswings.utils.adapters.PaymentAdapter

@Transactional
class PaymentService {
    public Payment save(PaymentAdapter paymentAdapter) {
        Payment payment = new Payment()

        payment.payer = paymentAdapter.payer

        payment.paymentType = paymentAdapter.paymentType

        payment.value = paymentAdapter.value

        payment.paymentStatus = PaymentStatus.WAITING_PAYMENT

        payment.dueDate = paymentAdapter.dueDate

        payment.save(failOnError: true)

        return payment
    }
}
