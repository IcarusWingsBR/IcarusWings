package icaruswings.utils.adapters

import icaruswings.utils.PaymentStatus
import icaruswings.utils.PaymentType
import icaruswings.Payer
import java.sql.Date
import java.text.SimpleDateFormat
import java.text.ParseException

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
        this.dueDate = parseDate(params.dueDate)
    }

    private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
        
        return new Date(sdf.parse(dateString).getTime())
    }
}