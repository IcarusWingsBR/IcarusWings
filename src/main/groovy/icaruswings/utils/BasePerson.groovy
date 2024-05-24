package icaruswings.utils

abstract class BasePerson extends BaseEntity {
    String name

    String email

    String cpfCnpj

    String postalCode

    String address

    String province

    String city

    String state

    Integer addressNumber

    String addressComplement

    String phone

    PersonType personType

    static constraints = {
        name nullable: false, blank: false
        email nullable: false, blank: false, email: true
        cpfCnpj nullable: false, blank: false
        postalCode nullable: false, blank: false
        address nullable: false, blank: false
        province nullable: false, blank: false
        city nullable: false, blank: false
        state nullable: false, blank: false
        addressNumber nullable: false
        addressComplement nullable: false
        phone nullable: false, blank: false
        personType nullable: false
    }
}