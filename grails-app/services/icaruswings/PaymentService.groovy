package icaruswings

import grails.gorm.transactions.Transactional
import icaruswings.utils.PaymentStatus
import icaruswings.utils.adapters.PaymentAdapter
import icaruswings.utils.repositories.PaymentRepository

@Transactional
class PaymentService {

    public Payment save(PaymentAdapter paymentAdapter) {
        Payment payment = new Payment()

        payment.payer = paymentAdapter.payer

        payment.paymentType = paymentAdapter.paymentType

        payment.paymentStatus = paymentAdapter.paymentStatus

        Double value = Double.parseDouble(paymentAdapter.value)
        payment.value = value

        payment.dueDate = paymentAdapter.dueDate

        payment.save(failOnError: true)

        return payment
    }

    public List<Payment> list(){
        return PaymentRepository.query([:]).list()
    }

    public void delete(Long id){
        Payment payment = PaymentRepository.get(id)

        if(!payment) throw new RuntimeException("Esse pagador não existe")

        payment.deleted = true

        payment.save(failOnError: true)
    }
}
