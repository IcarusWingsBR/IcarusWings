package icaruswings.utils.adapters

import icaruswings.Payer
import icaruswings.utils.PaymentStatus
import icaruswings.utils.PaymentType
import icaruswings.utils.date.DateParser
import java.sql.Date

class PaymentAdapter {

    String id

    Payer payer

    PaymentType paymentType

    PaymentStatus paymentStatus

    String value

    Date dueDate

    public PaymentAdapter(Map params) {
        this.id = params.id

        Long idPayer = Long.parseLong(params.idPayer)
        this.payer = Payer.get(idPayer)

        this.paymentType = PaymentType.convert(params.paymentType)
        this.paymentStatus = PaymentStatus.WAITING_PAYMENT
        this.value = params.value
        this.dueDate = DateParser.parse(params.dueDate)
    }
}