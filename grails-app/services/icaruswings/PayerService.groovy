package icaruswings

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import icaruswings.utils.PersonType
import icaruswings.utils.validations.ValidateCpfCnpj
import icaruswings.utils.validations.ValidateEmail
import icaruswings.utils.validations.ValidateName
import icaruswings.utils.validations.ValidatePhone

@Transactional
class PayerService {
    public Payer save(Map parsedParams) {
        Payer validatePayer = validatePayerParams(parsedParams)
        if (validatePayer.hasErrors()){
            throw new ValidationException("Não foi possível salvar o pagador",validatePayer.errors)
        }

        Payer payer = new Payer()

        payer.name = parsedParams.name
        payer.email = parsedParams.email
        if(ValidateCpfCnpj.isCPF(parsedParams.cpfCnpj)){
            payer.cpfCnpj = ValidateCpfCnpj.cleanCpf(parsedParams.cpfCnpj)
            payer.personType = PersonType.NATURAL
        } else if (ValidateCpfCnpj.isCNPJ(parsedParams.cpfCnpj)) {
            payer.cpfCnpj = ValidateCpfCnpj.cleanCnpj(parsedParams.cpfCnpj)
            payer.personType = PersonType.LEGAL
        }
        payer.phone = parsedParams.phone
        payer.customer = Customer.get(parsedParams.customerId)
        payer.personType = PersonType.NATURAL
        payer.save(failOnError: true)
        return payer
    }

    private Payer validatePayerParams(Map parsedParams) {
        Payer payer = new Payer()

        if (!parsedParams.cpfCnpj) {
            payer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj é obrigatório")
        } else if (!ValidateCpfCnpj.isCPF(parsedParams.cpfCnpj) && !ValidateCpfCnpj.isCNPJ(parsedParams.cpfCnpj)) {
            payer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj está inválido")
        }

        if (!parsedParams.name) {
            payer.errors.rejectValue("name", null, "O campo nome é obrigatório")
        } else if (!ValidateName.isValidName(parsedParams.name)) {
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

        return payer
    }
}