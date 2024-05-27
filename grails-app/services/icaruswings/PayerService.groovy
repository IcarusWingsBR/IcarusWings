package icaruswings

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import icaruswings.utils.PersonType
import icaruswings.utils.adapters.PayerAdapter
import icaruswings.utils.repositories.PayerRepository
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

        payer.postalCode = payerAdapter.postalCode

        payer.address = payerAdapter.address

        payer.province = payerAdapter.province

        payer.city = payerAdapter.city

        payer.state = payerAdapter.state

        payer.addressNumber = Integer.parseInt(payerAdapter.addressNumber)

        payer.addressComplement = payerAdapter.addressComplement

        payer.customer = payerAdapter.customer

        payer.phone = payerAdapter.phone

        payer.personType = payerAdapter.personType

        payer.save(failOnError: true)

        return payer
    }

    public void delete(Long id){
        Payer payer = PayerRepository.get(id)

        if(!payer) throw new RuntimeException("Esse pagador não existe")

        payer.deleted = true

        payer.save(failOnError: true)
    }

    public List<Payer> list(){
        return PayerRepository.query([:]).list()
    }

    private Payer validateSave(PayerAdapter payerAdapter) {
        Payer payer = new Payer()

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

        if (!payerAdapter.cpfCnpj) {
            payer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj é obrigatório")
        } else if (!ValidateCpfCnpj.isCPF(payerAdapter.cpfCnpj) && !ValidateCpfCnpj.isCNPJ(payerAdapter.cpfCnpj)) {
            payer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj está inválido")
        }

        if (!payerAdapter.phone) {
            payer.errors.rejectValue("phone", null, "O campo telefone é obrigatório")
        } else if (!ValidatePhone.isValidPhoneNumber(payerAdapter.phone)) {
            payer.errors.rejectValue("phone", null, "O numero de telefone inserido é inválido")
        }

        if (!payerAdapter.postalCode) {
            payer.errors.rejectValue("postalCode", null, "O campo cep é obrigatório")
        } else if (!PostalCodeValidator.isValid(payerAdapter.postalCode)) {
            payer.errors.rejectValue("postalCode", null, "O cep inserido é inválido")
        }

        if (!payerAdapter.address) {
            payer.errors.rejectValue("address", null, "O campo rua é obrigatório")
        }

        if (!payerAdapter.province) {
            payer.errors.rejectValue("province", null, "O campo bairro é obrigatório")
        }

        if (!payerAdapter.addressNumber) {
            payer.errors.rejectValue("addressNumber", null, "O campo número de residência é obrigatório")
        } else if (!StringUtils.containsOnlyNumbers(payerAdapter.addressNumber)) {
            payer.errors.rejectValue("addressNumber", null, "O número de residência é inválido")
        }

        if (!payerAdapter.addressComplement) {
            payer.errors.rejectValue("addressComplement", null, "O campo complemento é obrigatório")
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