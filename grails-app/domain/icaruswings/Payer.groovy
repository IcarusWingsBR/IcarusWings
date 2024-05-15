package icaruswings

import icaruswings.utils.PersonType

class Payer extends icaruswings.utils.BaseEntity {
    String name

    String email

    String cpfCnpj

    Customer customer

    PersonType personType

    static constraints = {
        name nullable: false, blank: false
        email nullable: false, blank: false, email: true
        customer nullable: false, blank:false
        cpfCnpj nullable: false, blank: false, size: 11..14, unique: true
        personType nullable: false, blank: false
    }
}