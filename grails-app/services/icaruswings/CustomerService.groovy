package icaruswings

import grails.gorm.transactions.Transactional
import icaruswings.utils.PersonType
import icaruswings.utils.validations.ValidateCpfCnpj
import icaruswings.utils.validations.ValidateEmail
import icaruswings.utils.validations.ValidateName

import grails.validation.ValidationException

@Transactional
class CustomerService {
    public Customer save(Map params) {
        Customer validateCustomer = validateCustomerParams(params)
        if (validateCustomer.hasErrors()){
            throw new ValidationException("Não foi possível salvar o cliente",validateCustomer.errors)
        }
        
        Customer customer = new Customer()
        customer.name = params.name
        customer.email = params.email
        if(ValidateCpfCnpj.isCPF(params.cpfCnpj)){
            customer.cpfCnpj = ValidateCpfCnpj.cleanCpf(params.cpfCnpj)
            customer.personType = PersonType.NATURAL
        } else if (ValidateCpfCnpj.isCNPJ(params.cpfCnpj)) {
            customer.cpfCnpj = ValidateCpfCnpj.cleanCnpj(params.cpfCnpj)
            customer.personType = PersonType.LEGAL
        }
        customer.save(failOnError: true)
        return customer
    }

    private Customer validateCustomerParams(Map params){
        Customer customer = new Customer()

        if(!params.cpfCnpj){
            customer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj é obrigatório")
        } else if (!ValidateCpfCnpj.isCPF(params.cpfCnpj) && !ValidateCpfCnpj.isCNPJ(params.cpfCnpj)) {
            customer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj está inválido")
        }

        if(!params.name){
            customer.errors.rejectValue("name",  null,"O campo nome é obrigatório")
        } else if (!ValidateName.isValidName(params.name)) {
            customer.errors.rejectValue("name", null, "O nome informado é inválido")
        }

        if(!params.email){
            customer.errors.rejectValue("email", null, "O campo email é obrigatório")
        } else if(!ValidateEmail.isValidEmail(params.email)){
            customer.errors.rejectValue("email", null, "O email informado é inválido")
        }
        return customer
    }
}

