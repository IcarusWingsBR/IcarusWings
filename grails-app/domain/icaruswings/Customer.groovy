package icaruswings

import icaruswings.utils.PersonType
import icaruswings.utils.BaseEntity

class Customer extends BaseEntity {
    String name

    String email

    String cpfCnpj

    PersonType personType

    static constraints = {
        name nullable: false, blank: false
        email nullable: false, blank: false, email: true
        cpfCnpj nullable: false, blank: false, size: 11..18
        personType nullable: false, blank: false
    }
}