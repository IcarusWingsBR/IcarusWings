package icaruswings

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import icaruswings.utils.PersonType
import icaruswings.utils.validator.PostalCodeValidator
import icaruswings.utils.validator.ValidateCpfCnpj
import icaruswings.utils.validator.ValidateEmail
import icaruswings.utils.validator.ValidatePhone
import icaruswings.utils.validator.StringUtils
import icaruswings.utils.validator.CheckEntityExistenceById

@Transactional
class PayerService {
    public Payer save(Map parsedParams) {

        Payer validatedPayer = validateSave(parsedParams)

        if (validatedPayer.hasErrors()) {
            throw new ValidationException("Não foi possível salvar o pagador", validatedPayer.errors)
        }

        Payer payer = new Payer()

        payer.name = parsedParams.name

        payer.email = parsedParams.email

        payer.cpfCnpj = parsedParams.cpfCnpj

        payer.personType = PersonType.NATURAL

        payer.postalCode = parsedParams.postalCode

        payer.address = parsedParams.address

        payer.province = parsedParams.province

        payer.city = parsedParams.city

        payer.state = parsedParams.state

        payer.addressNumber = Integer.parseInt(parsedParams.addressNumber)

        payer.addressComplement = parsedParams.addressComplement

        payer.customer = Customer.get(parsedParams.customerId)

        payer.phone = parsedParams.phone

        payer.save(failOnError: true)

        return payer
    }

    private Payer validateSave(Map parsedParams) {
        Payer payer = new Payer()

        if (!parsedParams.cpfCnpj) {
            payer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj é obrigatório")
        } else if (!ValidateCpfCnpj.isCPF(parsedParams.cpfCnpj) && !ValidateCpfCnpj.isCNPJ(parsedParams.cpfCnpj)) {
            payer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj está inválido")
        }

        if (!parsedParams.name) {
            payer.errors.rejectValue("name", null, "O campo nome é obrigatório")
        } else if (!StringUtils.isValidString(parsedParams.name)) {
            payer.errors.rejectValue("name", null, "O nome informado é inválido")
        }

        if (!parsedParams.email) {
            payer.errors.rejectValue("email", null, "O campo email é obrigatório")
        } else if (!ValidateEmail.isValidEmail(parsedParams.email)) {
            payer.errors.rejectValue("email", null, "O email informado é inválido")
        }

        if (!parsedParams.phone) {
            payer.errors.rejectValue("phone", null, "O campo telefone é obrigatório")
        } else if (!ValidatePhone.isValidPhoneNumber(parsedParams.phone)) {
            payer.errors.rejectValue("phone", null, "O numero de telefone inserido é inválido")
        }

        if (!parsedParams.postalCode) {
            payer.errors.rejectValue("postalCode", null, "O campo cep é obrigatório")
        } else if (!PostalCodeValidator.isValid(parsedParams.postalCode)) {
            payer.errors.rejectValue("postalCode", null, "O cep inserido é inválido")
        }

        if (!parsedParams.address) {
            payer.errors.rejectValue("address", null, "O campo rua é obrigatório")
        }

        if (!parsedParams.province) {
            payer.errors.rejectValue("province", null, "O campo bairro é obrigatório")
        }

        if (!parsedParams.addressNumber) {
            payer.errors.rejectValue("addressNumber", null, "O campo número de residência é obrigatório")
        } else if (!StringUtils.containsOnlyNumbers(parsedParams.addressNumber)) {
            payer.errors.rejectValue("addressNumber", null, "O número de residência é inválido")
        }

        if (!parsedParams.addressComplement) {
            payer.errors.rejectValue("addressComplement", null, "O campo complemento é obrigatório")
        }

        if (!parsedParams.city) {
            payer.errors.rejectValue("city", null, "O campo cidade é obrigatório")
        } else if (!StringUtils.isValidString(parsedParams.city)) {
            payer.errors.rejectValue("city", null, "A cidade informado é inválida")
        }

        if (!parsedParams.state) {
            payer.errors.rejectValue("state", null, "O campo estado é obrigatório")
        } else if (!StringUtils.isValidString(parsedParams.state)) {
            payer.errors.rejectValue("state", null, "O estado informado é inválido")
        }

        if (!parsedParams.customerId) {
            payer.errors.rejectValue("customerId", null, "O payer precisa estar vinculado a um customer")
        } else if (!CheckEntityExistenceById.CheckCustomerExistenceById(parsedParams.customerId)) {
            payer.errors.rejectValue("customerId", null, "O customer é inválido")
        }

        return payer
    }
}