package icaruswings.utils

abstract class BasePerson extends BaseEntity {
    String name

    String email

    String cpfCnpj

    String cep

    String street

    String neighborhood

    String city

    String state

    Integer number

    String complement

    PersonType personType

    static constraints = {
        name nullable: false, blank: false
        cep nullable: false, blank: false
        street nullable: false, blank: false
        neighborhood nullable: false, blank: false
        city nullable: false, blank: false
        state nullable: false, blank: false
        number nullable: false
        complement nullable: false
    }
}