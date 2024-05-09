package icaruswings

import icaruswings.utils.PersonType

class Payer extends icaruswings.utils.BaseEntity {
    String name
    String email
    String cpfCnpj
    Customer customer
    PersonType personType

    static constraints = {
        name nullable: true, blank: false
        email nullable: true, blank: false, email: true
        customer nullable: true, blank:false
        cpfCnpj nullable: true, blank: false, size: 11..14
        personType nullable: true, blank: false
    }
}