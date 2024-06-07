package icaruswings

import icaruswings.payment.Payment
import icaruswings.utils.BaseEntity

class Receipt extends BaseEntity {

    Payment payment

    String token
    
    static constraints = {
        payment nullable: false, blank: false
        token nullable: false, blank: false, unique: true
    }
}