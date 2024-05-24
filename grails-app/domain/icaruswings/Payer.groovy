package icaruswings

import icaruswings.utils.BasePerson

class Payer extends BasePerson {
    Customer customer

    static constraints = {
        customer nullable: false, blank: false
    }
}