package icaruswings

import grails.gorm.transactions.Transactional
import icaruswings.utils.adapters.PaymentAdapter
import icaruswings.utils.repositories.PaymentRepository

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

    public List<Payment> list(){
        return PaymentRepository.query([:]).list()
    }
}
