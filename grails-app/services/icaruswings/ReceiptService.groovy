package icaruswings

import grails.gorm.transactions.Transactional
import icaruswings.payment.Payment
import java.util.UUID

@Transactional
class ReceiptService {

    public Receipt save(Payment payment) {
        Receipt receipt = new Receipt()

        receipt.payment = payment
        receipt.token = UUID.randomUUID().toString()
        receipt.save(failOnError: true)
        
        return receipt
    }

}