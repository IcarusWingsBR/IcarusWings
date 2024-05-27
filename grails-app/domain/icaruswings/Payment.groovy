package icaruswings

import icaruswings.utils.PaymentStatus
import icaruswings.utils.PaymentType
import icaruswings.utils.BaseEntity
import java.sql.Date

class Payment extends BaseEntity{

    Payer payer

    PaymentType paymentType

    Double value

    PaymentStatus paymentStatus

    Date dueDate

    static constraints = {
        payer nullable: false, blank: false
        paymentType nullable: false, blank: false
        value nullable: false, blank: false
        paymentStatus nullable: false, blank: false
        dueDate nullable: false, blank: false
    }
}