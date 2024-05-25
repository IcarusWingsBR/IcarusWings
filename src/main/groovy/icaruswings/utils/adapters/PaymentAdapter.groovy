package icaruswings.utils.adapters

import icaruswings.utils.PaymentStatus
import icaruswings.utils.PaymentType
import icaruswings.utils.date.DateParser
import icaruswings.Payer
import java.sql.Date

class PaymentAdapter {

    Long id

    Payer payer

    PaymentType paymentType

    Double value

    Date dueDate

    public PaymentAdapter(Map params) {
        Long idPayer = Long.parseLong(params.id)
        this.payer = Payer.get(idPayer)
        this.paymentType = PaymentType.convert(params.paymentType)
        this.value = params.value
        this.dueDate = DateParser.parse(params.dueDate)
    }
}