package icaruswings

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import icaruswings.utils.PersonType
import icaruswings.utils.validations.ValidateCpfCnpj
import icaruswings.utils.validations.StringUtils
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

        customer.cpfCnpj = ValidateCpfCnpj.cleanCpfCnpj(parsedParams.cpfCnpj)

        if(ValidateCpfCnpj.isCPF(parsedParams.cpfCnpj)) {
            customer.personType = PersonType.NATURAL
        } else if (ValidateCpfCnpj.isCNPJ(parsedParams.cpfCnpj)) {
            customer.personType = PersonType.LEGAL
        }

        customer.cep = parsedParams.cep

        customer.street = parsedParams.street

        customer.neighborhood = parsedParams.neighborhood

        customer.city = parsedParams.city

        customer.state = parsedParams.state

        customer.number = Integer.parseInt(parsedParams.number)

        customer.complement = parsedParams.complement

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

        if(!parsedParams.cep) {
            payer.errors.rejectValue("cep", null, "O campo cep é obrigatório")
        } else if (!StringUtils.containsOnlyNumbers(parsedParams.cep)) {
            payer.errors.rejectValue("cep", null, "O cep inserido é inválido")
        }

        if(!parsedParams.street) {
            payer.errors.rejectValue("street", null, "O campo rua é obrigatório")
        }

        if(!parsedParams.neighborhood) {
            payer.errors.rejectValue("neighborhood", null, "O campo bairro é obrigatório")
        }

        if(!parsedParams.number) {
            payer.errors.rejectValue("number", null, "O campo número de residência é obrigatório")
        } else if (!StringUtils.containsOnlyNumbers(parsedParams.number)) {
            payer.errors.rejectValue("number", null, "O número de residência é inválido")
        }

        if(!parsedParams.complement) {
            payer.errors.rejectValue("complement", null, "O campo complemento é obrigatório")
        }

        if(!parsedParams.city) {
            payer.errors.rejectValue("city", null, "O campo cidade é obrigatório")
        } else if (!StringUtils.isValidString(parsedParams.city)) {
            payer.errors.rejectValue("city", null, "A cidade informado é inválida")
        }

        if(!parsedParams.state) {
            payer.errors.rejectValue("state", null, "O campo estado é obrigatório")
        } else if (!StringUtils.isValidString(parsedParams.state)) {
            payer.errors.rejectValue("state", null, "O estado informado é inválido")
        }

        return customer
    }
}