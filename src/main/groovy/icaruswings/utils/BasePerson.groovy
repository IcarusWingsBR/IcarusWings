package icaruswings.utils

abstract class BasePerson extends BaseEntity{
    String cep
    String street
    String neighborhood
    String city
    String state
    int number
    String details

    static constraints = {
        cep nullable: true, blank: false
        street nullable: true, blank: false
        neighborhood nullable: true, blank: false
        city nullable: true, blank: false
        state nullable: true, blank: false
        number nullable: true
        details nullable: true
    }
}