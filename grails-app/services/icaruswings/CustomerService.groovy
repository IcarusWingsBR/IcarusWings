package icaruswings

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import icaruswings.utils.PersonType
import icaruswings.utils.validations.ValidateCpfCnpj
import icaruswings.utils.validations.ValidateName
import icaruswings.utils.validations.ValidateEmail

@Transactional
class CustomerService {

    public Customer save(Map parsedParams) {

        Customer validateCustomer = validateCustomerParams(parsedParams)

        if (validateCustomer.hasErrors()) {
            throw new ValidationException("Não foi possível salvar o cliente", validateCustomer.errors)
        }

        Customer customer = new Customer()

        customer.name = parsedParams.name

        customer.email = parsedParams.email

        if(ValidateCpfCnpj.isCPF(parsedParams.cpfCnpj)) {
            customer.cpfCnpj = ValidateCpfCnpj.cleanCpfCnpj(parsedParams.cpfCnpj)
            customer.personType = PersonType.NATURAL
        } else if (ValidateCpfCnpj.isCNPJ(parsedParams.cpfCnpj)) {
            customer.cpfCnpj = ValidateCpfCnpj.cleanCpfCnpj(parsedParams.cpfCnpj)
            customer.personType = PersonType.LEGAL
        }

        customer.cep = parsedParams.cep

        customer.street = parsedParams.street

        customer.neighborhood = parsedParams.neighborhood

        customer.city = parsedParams.city

        customer.state = parsedParams.state

        customer.number = Integer.parseInt(parsedParams.number)

        customer.complement = parsedParams.complement
        
        customer.personType = PersonType.NATURAL

        customer.save(failOnError: true)

        return customer
    }

    private Customer validateCustomerParams(Map parsedParams) {
        Customer customer = new Customer()

        if(!parsedParams.name) {
            customer.errors.rejectValue("name",  null,"O campo nome é obrigatório")
        } else if (!ValidateName.isValidName(parsedParams.name)) {
            customer.errors.rejectValue("name", null, "O nome informado é inválido")
        }

        if(!parsedParams.email) {
            customer.errors.rejectValue("email", null, "O campo email é obrigatório")
        } else if(!ValidateEmail.isValidEmail(parsedParams.email)){
            customer.errors.rejectValue("email", null, "O email informado é inválido")
        }

        if(!parsedParams.cpfCnpj) {
            customer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj é obrigatório")
        } else if (!ValidateCpfCnpj.isCPF(parsedParams.cpfCnpj) && !ValidateCpfCnpj.isCNPJ(parsedParams.cpfCnpj)) {
            customer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj está inválido")
        }

        return customer
    }
}