package icaruswings

class Payer {
    String name

    String email

    String cpfCnpj

    Customer customer

    PersonType personType

    static constraints = {
        name nullable: false, blank: false
        email nullable: false, blank: false, email: true
        customer nullable: false, blank:false
        cpfCnpj nullable: false, blank: false, size: 11..18
        personType nullable: false, blank: false
    }
}