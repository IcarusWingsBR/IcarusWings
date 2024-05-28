package icaruswings.utils.adapters

import icaruswings.Payer
import icaruswings.utils.PaymentStatus
import icaruswings.utils.PaymentType
import icaruswings.utils.bigDecimal.BigDecimalUtis
import icaruswings.utils.date.DateUtils

class PaymentAdapter {

    Long id

    Payer payer

    PaymentType paymentType

    PaymentStatus paymentStatus

    BigDecimal value

    Date dueDate

    public PaymentAdapter(Map params) {
        this.id = params.id

        Long idPayer = Long.parseLong(params.idPayer)
        this.payer = Payer.get(idPayer)

        this.paymentType = PaymentType.convert(params.paymentType)
        this.paymentStatus = PaymentStatus.WAITING_PAYMENT
        this.value = BigDecimalUtis.parse(params.value)
        this.dueDate = DateUtils.parse(params.dueDate)
    }
}