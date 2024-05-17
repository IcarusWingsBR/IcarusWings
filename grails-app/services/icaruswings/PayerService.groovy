package icaruswings

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import icaruswings.utils.PersonType
import icaruswings.utils.validations.ValidateCpfCnpj
import icaruswings.utils.validations.ValidateEmail
import icaruswings.utils.validations.ValidatePhone
import icaruswings.utils.validations.StringUtils

@Transactional
class PayerService {
    public Payer save(Map parsedParams) {
        
        Payer validatePayer = validatePayerParams(parsedParams)

        if(validatePayer.hasErrors()) {
            throw new ValidationException("Não foi possível salvar o pagador", validatePayer.errors)
        }

        Payer payer = new Payer()

        payer.name = parsedParams.name

        payer.email = parsedParams.email

        payer.cpfCnpj = ValidateCpfCnpj.cleanCpfCnpj(parsedParams.cpfCnpj)

        if(ValidateCpfCnpj.isCPF(parsedParams.cpfCnpj)) {
            payer.personType = PersonType.NATURAL
        } else if (ValidateCpfCnpj.isCNPJ(parsedParams.cpfCnpj)) {
            payer.personType = PersonType.LEGAL
        }

        payer.cep = parsedParams.cep

        payer.street = parsedParams.street

        payer.neighborhood = parsedParams.neighborhood

        payer.city = parsedParams.city

        payer.state = parsedParams.state

        payer.number = Integer.parseInt(parsedParams.number)

        payer.complement = parsedParams.complement

        payer.customer = Customer.get(parsedParams.customerId)

        payer.phoneNumber = parsedParams.phoneNumber

        payer.save(failOnError: true)

        return payer
    }

    private Payer validatePayerParams(Map parsedParams) {
        Payer payer = new Payer()
        
        if(!parsedParams.cpfCnpj) {
            payer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj é obrigatório")
        } else if (!ValidateCpfCnpj.isCPF(parsedParams.cpfCnpj) && !ValidateCpfCnpj.isCNPJ(parsedParams.cpfCnpj)) {
            payer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj está inválido")
        }

        if(!parsedParams.name) {
            payer.errors.rejectValue("name", null, "O campo nome é obrigatório")
        } else if (!StringUtils.isValidString(parsedParams.name)) {
            payer.errors.rejectValue("name", null, "O nome informado é inválido")
        }

        if(!parsedParams.email) {
            payer.errors.rejectValue("email", null, "O campo email é obrigatório")
        } else if (!ValidateEmail.isValidEmail(parsedParams.email)) {
            payer.errors.rejectValue("email", null, "O email informado é inválido")
        }

        if(!parsedParams.phoneNumber) {
            payer.errors.rejectValue("phone", null, "O campo telefone é obrigatório")
        } else if (!ValidatePhone.isValidPhoneNumber(parsedParams.phoneNumber)) {
            payer.errors.rejectValue("phone", null, "O numero de telefone inserido é inválido")
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

        if(!parsedParams.customerId) {
            payer.errors.rejectValue("customerId", null, "O payer precisa estar vinculado a um customer")
        } else if(!isCustomerIdValid(parsedParams.customerId)) {
            payer.errors.rejectValue("customerId", null, "O customer é inválido")
        }

        return payer
    }

    private Boolean isCustomerIdValid(Long customerId) {
        Customer customer = Customer.get(customerId)
 
        if(!customer || customer.deleted) return false

        return true
    }
}