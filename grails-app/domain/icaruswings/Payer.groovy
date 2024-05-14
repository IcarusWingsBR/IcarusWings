package icaruswings

import icaruswings.utils.PersonType
import icaruswings.utils.BasePerson

class Payer extends BasePerson {
    String name
    String email
    String cpfCnpj
    String phone
    Customer customer
    PersonType personType

    static constraints = {
        name nullable: true, blank: false
        email nullable: true, blank: false, email: true
        customer nullable: true, blank:false
        cpfCnpj nullable: true, blank: false, size: 11..18
        phone nullable: true, blank: false
        personType nullable: true, blank: false
    }
}