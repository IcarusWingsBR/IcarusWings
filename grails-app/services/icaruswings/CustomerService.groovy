package icaruswings

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import icaruswings.utils.PersonType
import icaruswings.utils.adapters.CustomerAdapter
import icaruswings.utils.validations.ValidateCep
import icaruswings.utils.validations.ValidateCpfCnpj
import icaruswings.utils.validations.StringUtils
import icaruswings.utils.validations.ValidateEmail

@Transactional
class CustomerService {

    public Customer save(CustomerAdapter customerAdapter) {

        Customer validateCustomer = validateCustomerParams(customerAdapter)

        if (validateCustomer.hasErrors()) {
            throw new ValidationException("Não foi possível salvar o cliente", validateCustomer.errors)
        }

        Customer customer = createCustomerFromAdapter(customerAdapter, new Customer())

        customer.save(failOnError: true)

        return customer
    }

    private Customer validateCustomerParams(CustomerAdapter customerAdapter) {
        Customer customer = new Customer()

        if (!customerAdapter.name) {
            customer.errors.rejectValue("name",  null,"O campo nome é obrigatório")
        } else if (!StringUtils.isValidString(customerAdapter.name)) {
            customer.errors.rejectValue("name", null, "O nome informado é inválido")
        }

        if (!customerAdapter.email) {
            customer.errors.rejectValue("email", null, "O campo email é obrigatório")
        } else if(!ValidateEmail.isValidEmail(customerAdapter.email)){
            customer.errors.rejectValue("email", null, "O email informado é inválido")
        }

        if (!customerAdapter.cpfCnpj) {
            customer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj é obrigatório")
        } else if (!ValidateCpfCnpj.isCPF(customerAdapter.cpfCnpj) && !ValidateCpfCnpj.isCNPJ(customerAdapter.cpfCnpj)) {
            customer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj está inválido")
        } else if(checkIfCpfOrCnpjExists(customerAdapter.cpfCnpj)) {
            customer.errors.rejectValue("cpfCnpj", null, "O Cpf/Cnpj já está cadastrado")
        }

        if (!customerAdapter.cep) {
            customer.errors.rejectValue("cep", null, "O campo cep é obrigatório")
        } else if (!ValidateCep.isValidCep(customerAdapter.cep)) {
            customer.errors.rejectValue("cep", null, "O cep inserido é inválido")
        }

        if (!customerAdapter.street) {
            customer.errors.rejectValue("street", null, "O campo rua é obrigatório")
        }

        if (!customerAdapter.neighborhood) {
            customer.errors.rejectValue("neighborhood", null, "O campo bairro é obrigatório")
        }

        if (!customerAdapter.number) {
            customer.errors.rejectValue("number", null, "O campo número de residência é obrigatório")
        } else if (!StringUtils.containsOnlyNumbers(customerAdapter.number)) {
            customer.errors.rejectValue("number", null, "O número de residência é inválido")
        }

        if (!customerAdapter.complement) {
            customer.errors.rejectValue("complement", null, "O campo complemento é obrigatório")
        }

        if (!customerAdapter.city) {
            customer.errors.rejectValue("city", null, "O campo cidade é obrigatório")
        } else if (!StringUtils.isValidString(customerAdapter.city)) {
            customer.errors.rejectValue("city", null, "A cidade informado é inválida")
        }

        if (!customerAdapter.state) {
            customer.errors.rejectValue("state", null, "O campo estado é obrigatório")
        } else if (!StringUtils.isValidString(customerAdapter.state)) {
            customer.errors.rejectValue("state", null, "O estado informado é inválido")
        }

        return customer
    }

    public Boolean checkIfCpfOrCnpjExists(String cpfCnpj) {
        String sanitizedCpfCnpj = ValidateCpfCnpj.cleanCpfCnpj(cpfCnpj)
        Customer customer = Customer.findByCpfCnpj(sanitizedCpfCnpj)

        if (customer == null) return false

        return true
    }

    private Customer createCustomerFromAdapter(CustomerAdapter customerAdapter, Customer customer) {
        customer.name = customerAdapter.name

        customer.email = customerAdapter.email

        customer.cpfCnpj = ValidateCpfCnpj.cleanCpfCnpj(customerAdapter.cpfCnpj)

        customer.cep = customerAdapter.cep

        customer.street = customerAdapter.street

        customer.neighborhood = customerAdapter.neighborhood

        customer.city = customerAdapter.city

        customer.state = customerAdapter.state

        customer.number = Integer.parseInt(customerAdapter.number)

        customer.complement = customerAdapter.complement

        if(ValidateCpfCnpj.isCPF(customerAdapter.cpfCnpj)) {
            customer.personType = PersonType.NATURAL
        } else if (ValidateCpfCnpj.isCNPJ(customerAdapter.cpfCnpj)) {
            customer.personType = PersonType.LEGAL
        }

        return customer
    }
}