package icaruswings.utils.adapters
import icaruswings.utils.PersonType
import icaruswings.utils.validations.StringUtils
import icaruswings.utils.validations.ValidateCpfCnpj
import icaruswings.utils.validations.ValidateEmail

class CustomerAdapter {

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

    public CustomerAdapter(Map params) {
        this.name = StringUtils.isValidString(params.name)

        this.email = ValidateEmail.isValidEmail(params.email)

        if(ValidateCpfCnpj.isCPF(params.cpfCnpj)) {
            this.personType = PersonType.NATURAL
        } else if (ValidateCpfCnpj.isCNPJ(params.cpfCnpj)) {
            this.personType = PersonType.LEGAL
        }

        this.cep = params.cep

        this.street = params.street

        this.neighborhood = params.neighborhood

        this.city = params.city

        this.state = params.state

        this.number = params.number

        this.complement = params.complement

        this.personType = params.personType
    }
}