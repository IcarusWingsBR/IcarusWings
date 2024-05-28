package icaruswings

import grails.gorm.transactions.Transactional
import icaruswings.utils.PaymentStatus
import icaruswings.utils.adapters.PaymentAdapter
import icaruswings.utils.bigDecimal.BigDecimalUtis

@Transactional
class PaymentService {

    public Payment save(PaymentAdapter paymentAdapter) {
        Payment payment = new Payment()

        payment.payer = paymentAdapter.payer
        payment.paymentType = paymentAdapter .paymentType
        payment.paymentStatus = paymentAdapter.paymentStatus
        payment.value = paymentAdapter.value
        payment.dueDate = paymentAdapter.dueDate

        payment.save(failOnError: true)

        return payment
    }
}
