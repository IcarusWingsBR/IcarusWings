package icaruswings

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import icaruswings.utils.adapters.CustomerAdapter
import icaruswings.utils.repositories.CustomerRepository
import icaruswings.utils.validator.ValidateCpfCnpj
import icaruswings.utils.validator.StringUtils
import icaruswings.utils.validator.ValidateEmail
import icaruswings.utils.validator.PostalCodeValidator
import icaruswings.utils.validator.ValidatePhone
import icaruswings.utils.repositories.CustomerRepository

@Transactional
class CustomerService {

    public Customer save(CustomerAdapter customerAdapter) {
        Customer validatedCustomer = validateSave(customerAdapter)

        if (validatedCustomer.hasErrors()) throw new ValidationException("Não foi possível salvar o cliente", validatedCustomer.errors)

        Customer customer = new Customer()
        customer.name = customerAdapter.name
        customer.email = customerAdapter.email
        customer.cpfCnpj = customerAdapter.cpfCnpj
        customer.postalCode = customerAdapter.postalCode
        customer.address = customerAdapter.address
        customer.province = customerAdapter.province
        customer.city = customerAdapter.city
        customer.state = customerAdapter.state
        customer.addressNumber = customerAdapter.addressNumber
        customer.addressComplement = customerAdapter.addressComplement
        customer.phone= customerAdapter.phone
        customer.personType = customerAdapter.personType
        customer.save(failOnError: true)

        return customer
    }

    public void update(CustomerAdapter customerAdapter) {      
        Customer validatedCustomer = validateDefaultFields(customerAdapter)

        if (validatedCustomer.hasErrors()) throw new ValidationException("Não foi possível salvar a conta", validatedCustomer.errors)

        Customer customer = CustomerRepository.get(customerAdapter.id)
        customer.name = customerAdapter.name
        customer.email = customerAdapter.email
        customer.phone = customerAdapter.phone
        customer.postalCode = customerAdapter.postalCode
        customer.address = customerAdapter.address
        customer.province = customerAdapter.province
        customer.city = customerAdapter.city
        customer.state = customerAdapter.state
        customer.addressNumber = customerAdapter.addressNumber
        customer.addressComplement = customerAdapter.addressComplement
        customer.save(failOnError: true)
    }

    public List<Customer> list(){
        return CustomerRepository.query([:]).list()
    }

    private Customer validateSave(CustomerAdapter customerAdapter) {
        Customer customer = validateDefaultFields(customerAdapter)

        if (!customerAdapter.cpfCnpj) {
            customer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj é obrigatório")
        } else if (!ValidateCpfCnpj.isCPF(customerAdapter.cpfCnpj) && !ValidateCpfCnpj.isCNPJ(customerAdapter.cpfCnpj)) {
            customer.errors.rejectValue("cpfCnpj", null, "O campo Cpf/Cnpj está inválido")
        } else if (checkIfCpfOrCnpjExists(customerAdapter.cpfCnpj)) {
            customer.errors.rejectValue("cpfCnpj", null, "O Cpf/Cnpj já está cadastrado")
        }

        return customer
    }

    private Customer validateDefaultFields(CustomerAdapter customerAdapter) {
        Customer customer = new Customer()

        if (!customerAdapter.name) {
            customer.errors.rejectValue("name",  null,"O campo nome é obrigatório")
        } else if (!StringUtils.isValidString(customerAdapter.name)) {
            customer.errors.rejectValue("name", null, "O nome informado é inválido")
        }

        if (!customerAdapter.email) {
            customer.errors.rejectValue("email", null, "O campo email é obrigatório")
        } else if (!ValidateEmail.isValidEmail(customerAdapter.email)){
            customer.errors.rejectValue("email", null, "O email informado é inválido")
        }

        if (!customerAdapter.postalCode) {
            customer.errors.rejectValue("postalCode", null, "O campo cep é obrigatório")
        } else if (!PostalCodeValidator.isValid(customerAdapter.postalCode)) {
            customer.errors.rejectValue("postalCode", null, "O cep inserido é inválido")
        }

        if (!customerAdapter.address) {
            customer.errors.rejectValue("address", null, "O campo rua é obrigatório")
        }

        if (!customerAdapter.province) {
            customer.errors.rejectValue("province", null, "O campo bairro é obrigatório")
        }

        if (!customerAdapter.addressNumber) {
            customer.errors.rejectValue("addressNumber", null, "O campo número de residência é obrigatório")
        }

        if (!customerAdapter.addressComplement) {
            customer.errors.rejectValue("addressComplement", null, "O campo complemento é obrigatório")
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

        if (!customerAdapter.phone) {
            customer.errors.rejectValue("phone", null, "O campo telefone é obrigatório")
        } else if (!ValidatePhone.isValidPhoneNumber(customerAdapter.phone)) {
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