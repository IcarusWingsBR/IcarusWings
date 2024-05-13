package icaruswings

import grails.gorm.transactions.Transactional
import icaruswings.utils.PersonType
import icaruswings.utils.ValidateCpfCnpj
import icaruswings.utils.ValidateEmail

import grails.validation.ValidationException

@Transactional
class CustomerService {
    public Customer save(Map params) {
        Customer validateCustomer = validadeCustomerParams(params)
        if (validateCustomer.hasErrors()){
            throw new ValidationException("Não foi possível salvar o cliente",validateCustomer.errors)
        }
        Customer customer = new Customer()
        customer.name = params.name
        customer.email = params.email
        customer.cpfCnpj = ValidateCpfCnpj.cleanCpf(params.cpfCnpj)
        if(ValidateCpfCnpj.isCPF(params.cpfCnpj)){
            customer.personType = PersonType.NATURAL
        } else{
            customer.personType = PersonType.LEGAL
        }
        customer.save(failOnError: true)
        return customer
    }

    private Customer validadeCustomerParams(Map params){
        Customer customer = new Customer()

        if(!params.cpfCnpj){
            customer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj é obrigatório")
        } else if (!ValidateCpfCnpj.isCPF(params.cpfCnpj)) {//verificar se é cpf cnpj com metodo que valida os dois ao mesmo tempo
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

