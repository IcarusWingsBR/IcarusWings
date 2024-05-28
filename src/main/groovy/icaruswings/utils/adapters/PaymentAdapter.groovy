package icaruswings.utils.adapters

import icaruswings.Payer
import icaruswings.utils.PaymentStatus
import icaruswings.utils.PaymentType
import icaruswings.utils.bigDecimal.BigDecimalUtis
import icaruswings.utils.date.DateUtils
import icaruswings.utils.repositories.PayerRepository

class PaymentAdapter {

    String id

    Payer payer

    PaymentType paymentType

    PaymentStatus paymentStatus

    BigDecimal value

    Date dueDate

    public PaymentAdapter(Map params) {
        if (params.id) this.id = Long.valueOf(params.id)

        Long payerId = Long.parseLong(params.payerId)
        this.payer = PayerRepository.get(payerId)
        this.paymentType = PaymentType.convert(params.paymentType)
        this.paymentStatus = PaymentStatus.WAITING_PAYMENT
        this.value = BigDecimalUtis.parse(params.value)
        this.dueDate = DateUtils.parse(params.dueDate)
    }
}