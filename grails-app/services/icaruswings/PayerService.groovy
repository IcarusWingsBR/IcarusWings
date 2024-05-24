package icaruswings

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import icaruswings.utils.PersonType
import icaruswings.utils.adapters.PayerAdapter
import icaruswings.utils.validator.PostalCodeValidator
import icaruswings.utils.validator.ValidateCpfCnpj
import icaruswings.utils.validator.ValidateEmail
import icaruswings.utils.validator.ValidatePhone
import icaruswings.utils.validator.StringUtils

@Transactional
class PayerService {
    public Payer save(PayerAdapter payerAdapter) {

        Payer validatedPayer = validateSave(payerAdapter)

        if (validatedPayer.hasErrors()) {
            throw new ValidationException("Não foi possível salvar o pagador", validatedPayer.errors)
        }

        Payer payer = new Payer()

        payer.name = payerAdapter.name

        payer.email = payerAdapter.email

        payer.cpfCnpj = ValidateCpfCnpj.cleanCpfCnpj(payerAdapter.cpfCnpj)

        payer.cep = payerAdapter.postalCode

        payer.street = payerAdapter.address

        payer.neighborhood = payerAdapter.province

        payer.city = payerAdapter.city

        payer.state = payerAdapter.state

        payer.number = Integer.parseInt(payerAdapter.addressNumber)

        payer.complement = payerAdapter.addressComplement

        payer.customer = payerAdapter.customer

        payer.phoneNumber = payerAdapter.phoneNumber

        if (ValidateCpfCnpj.isCPF(payerAdapter.cpfCnpj)) {
            payer.personType = PersonType.NATURAL
        } else if (ValidateCpfCnpj.isCNPJ(payerAdapter.cpfCnpj)) {
            payer.personType = PersonType.LEGAL
        }

        payer.save(failOnError: true)

        return payer
    }

    public Payer update(PayerAdapter payerAdapter) {
        Long id = Long.valueOf(payerAdapter.id)
        Payer payer = Payer.get(id)

        if (!payer) throw new RuntimeException("Pagador nao encontrado")

        Payer validatedPayer = validateSave(payerAdapter)

        if (validatedPayer.hasErrors()) {
            throw new ValidationException("Não foi possível salvar o pagador", validatePayer.errors)
        }

        payer.properties = payerAdapter.properties

        payer.save(failOnError: true)

        return payer
    }


    private Payer validateSave(PayerAdapter payerAdapter) {
        Payer payer = new Payer()
        
        if (!payerAdapter.cpfCnpj) {
            payer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj é obrigatório")
        } else if (!ValidateCpfCnpj.isCPF(payerAdapter.cpfCnpj) && !ValidateCpfCnpj.isCNPJ(payerAdapter.cpfCnpj)) {
            payer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj está inválido")
        }

        if (!payerAdapter.name) {
            payer.errors.rejectValue("name", null, "O campo nome é obrigatório")
        } else if (!StringUtils.isValidString(payerAdapter.name)) {
            payer.errors.rejectValue("name", null, "O nome informado é inválido")
        }

        if (!payerAdapter.email) {
            payer.errors.rejectValue("email", null, "O campo email é obrigatório")
        } else if (!ValidateEmail.isValidEmail(payerAdapter.email)) {
            payer.errors.rejectValue("email", null, "O email informado é inválido")
        }

        if (!payerAdapter.phoneNumber) {
            payer.errors.rejectValue("phoneNumber", null, "O campo telefone é obrigatório")
        } else if (!ValidatePhone.isValidPhoneNumber(payerAdapter.phoneNumber)) {
            payer.errors.rejectValue("phoneNumber", null, "O numero de telefone inserido é inválido")
        }

        if (!payerAdapter.postalCode) {
            payer.errors.rejectValue("cep", null, "O campo cep é obrigatório")
        } else if (!PostalCodeValidator.isValid(payerAdapter.postalCode)) {
            payer.errors.rejectValue("cep", null, "O cep inserido é inválido")
        }

        if (!payerAdapter.address) {
            payer.errors.rejectValue("street", null, "O campo rua é obrigatório")
        }

        if (!payerAdapter.province) {
            payer.errors.rejectValue("neighborhood", null, "O campo bairro é obrigatório")
        }
        
        if (!payerAdapter.addressNumber) {
            payer.errors.rejectValue("number", null, "O campo número de residência é obrigatório")
        } else if (!StringUtils.containsOnlyNumbers(payerAdapter.addressNumber)) {
            payer.errors.rejectValue("number", null, "O número de residência é inválido")
        }

        if (!payerAdapter.addressComplement) {
            payer.errors.rejectValue("complement", null, "O campo complemento é obrigatório")
        }

        if (!payerAdapter.city) {
            payer.errors.rejectValue("city", null, "O campo cidade é obrigatório")
        } else if (!StringUtils.isValidString(payerAdapter.city)) {
            payer.errors.rejectValue("city", null, "A cidade informado é inválida")
        }

        if (!payerAdapter.state) {
            payer.errors.rejectValue("state", null, "O campo estado é obrigatório")
        } else if (!StringUtils.isValidString(payerAdapter.state)) {
            payer.errors.rejectValue("state", null, "O estado informado é inválido")
        }

        if (!payerAdapter.customer.id) {
            payer.errors.rejectValue("customerId", null, "O payer precisa estar vinculado a um customer")
        } else if (!isCustomerIdValid(payerAdapter.customer.id)) {
            payer.errors.rejectValue("customerId", null, "O customer é inválido")
        }

        return payer
    }

    private Boolean isCustomerIdValid(Long customerId) {
        Customer customer = Customer.get(customerId)
 
        if (!customer || customer.deleted) return false

        return true
    }
}