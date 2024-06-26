package icaruswings.adapters

import icaruswings.Customer
import icaruswings.Payer
import icaruswings.payment.PaymentStatus
import icaruswings.payment.PaymentType
import icaruswings.utils.bigDecimal.BigDecimalUtis
import icaruswings.utils.date.DateUtils
import icaruswings.repositories.PayerRepository

class PaymentAdapter {

    Long id

    Customer customer

    Payer payer

    PaymentType paymentType

    PaymentStatus paymentStatus

    BigDecimal value

    Date dueDate

    public PaymentAdapter(Customer customer, Map params) {
        if (params.id) this.id = Long.valueOf(params.id)

        this.customer = customer
        Long payerId = Long.parseLong(params.payerId)
        this.payer = PayerRepository.get(payerId)
        this.paymentType = PaymentType.convert(params.paymentType)
        this.paymentStatus = PaymentStatus.PENDING
        this.value = BigDecimalUtis.parse(params.value)
        this.dueDate = DateUtils.parse(params.dueDate)
    }
}