package icaruswings.payment

import icaruswings.Payer
import icaruswings.utils.BaseEntity

class Payment extends BaseEntity {

    Payer payer

    PaymentType paymentType

    BigDecimal value

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