package icaruswings

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import icaruswings.utils.PersonType
import icaruswings.utils.validator.ValidateCpfCnpj
import icaruswings.utils.validator.StringUtils
import icaruswings.utils.validator.ValidateEmail
import icaruswings.utils.validator.PostalCodeValidator
import icaruswings.utils.validator.ValidatePhone

@Transactional
class CustomerService {

    public Customer save(Map parsedParams) {
        Customer validateCustomer = validateSave(parsedParams)

        if (validateCustomer.hasErrors()) {
            throw new ValidationException("Não foi possível salvar o cliente", validateCustomer.errors)
        }

        Customer customer = new Customer()

        customer.name = parsedParams.name

        customer.email = parsedParams.email

        customer.cpfCnpj = parsedParams.cpfCnpj

        customer.personType = PersonType.NATURAL

        customer.postalCode = parsedParams.postalCode

        customer.address = parsedParams.address

        customer.province = parsedParams.province

        customer.city = parsedParams.city

        customer.state = parsedParams.state

        customer.addressNumber = Integer.parseInt(parsedParams.addressNumber)

        customer.addressComplement = parsedParams.addressComplement

        customer.phone = parsedParams.phone

        customer.save(failOnError: true)

        return customer
    }

    private Customer validateSave(Map parsedParams) {
        Customer customer = new Customer()

        if (!parsedParams.name) {
            customer.errors.rejectValue("name",  null,"O campo nome é obrigatório")
        } else if (!StringUtils.isValidString(parsedParams.name)) {
            customer.errors.rejectValue("name", null, "O nome informado é inválido")
        }

        if (!parsedParams.email) {
            customer.errors.rejectValue("email", null, "O campo email é obrigatório")
        } else if (!ValidateEmail.isValidEmail(parsedParams.email)){
            customer.errors.rejectValue("email", null, "O email informado é inválido")
        }

        if (!parsedParams.cpfCnpj) {
            customer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj é obrigatório")
        } else if (!ValidateCpfCnpj.isCPF(parsedParams.cpfCnpj) && !ValidateCpfCnpj.isCNPJ(parsedParams.cpfCnpj)) {
            customer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj está inválido")
        } else if (checkIfCpfOrCnpjExists(parsedParams.cpfCnpj)) {
            customer.errors.rejectValue("cpfCnpj", null, "O Cpf/Cnpj já está cadastrado")
        }

        if (!parsedParams.postalCode) {
            customer.errors.rejectValue("postalCode", null, "O campo cep é obrigatório")
        } else if (!PostalCodeValidator.isValid(parsedParams.postalCode)) {
            customer.errors.rejectValue("postalCode", null, "O cep inserido é inválido")
        }

        if (!parsedParams.address) {
            customer.errors.rejectValue("address", null, "O campo rua é obrigatório")
        }

        if (!parsedParams.province) {
            customer.errors.rejectValue("province", null, "O campo bairro é obrigatório")
        }

        if (!parsedParams.addressNumber) {
            customer.errors.rejectValue("addressNumber", null, "O campo número de residência é obrigatório")
        } else if (!StringUtils.containsOnlyNumbers(parsedParams.addressNumber)) {
            customer.errors.rejectValue("addressNumber", null, "O número de residência é inválido")
        }

        if (!parsedParams.addressComplement) {
            customer.errors.rejectValue("addressComplement", null, "O campo complemento é obrigatório")
        }

        if (!parsedParams.city) {
            customer.errors.rejectValue("city", null, "O campo cidade é obrigatório")
        } else if (!StringUtils.isValidString(parsedParams.city)) {
            customer.errors.rejectValue("city", null, "A cidade informado é inválida")
        }

        if (!parsedParams.state) {
            customer.errors.rejectValue("state", null, "O campo estado é obrigatório")
        } else if (!StringUtils.isValidString(parsedParams.state)) {
            customer.errors.rejectValue("state", null, "O estado informado é inválido")
        }

        if (!parsedParams.phone) {
            customer.errors.rejectValue("phone", null, "O campo telefone é obrigatório")
        } else if (!ValidatePhone.isValidPhoneNumber(parsedParams.phone)) {
            customer.errors.rejectValue("phone", null, "O numero de telefone inserido é inválido")
        }

        return customer
    }

    public Boolean checkIfCpfOrCnpjExists(String cpfCnpj) {
        String sanitizedCpfCnpj = ValidateCpfCnpj.cleanCpfCnpj(cpfCnpj)
        Customer customer = Customer.findByCpfCnpj(sanitizedCpfCnpj)

        if (customer == null) return false

        return true
    }
}