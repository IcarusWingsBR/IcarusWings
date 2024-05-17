package icaruswings

import icaruswings.utils.BasePerson

class Payer extends BasePerson {
    Customer customer

    String phoneNumber

    static constraints = {
        phoneNumber nullable: false, blank: false
        customer nullable: false, blank: false
    }
}