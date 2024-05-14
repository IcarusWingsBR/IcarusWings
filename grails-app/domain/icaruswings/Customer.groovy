package icaruswings

import icaruswings.utils.PersonType
import icaruswings.utils.BasePerson

class Customer extends BasePerson {
    String name
    String email
    String cpfCnpj
    PersonType personType

    static constraints = {
        name nullable: true, blank: false
        email nullable: true, blank: false, email: true
        cpfCnpj nullable: true, blank: false, size: 11..18
        personType nullable: true, blank: false
    }
}

