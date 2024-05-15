package icaruswings

import icaruswings.utils.PersonType
import icaruswings.utils.BasePerson

class Customer extends BasePerson{

    static constraints = {
        email nullable: false, blank: false, email: true, unique: true
        cpfCnpj nullable: false, blank: false, size: 11..18, unique: true
    }
}