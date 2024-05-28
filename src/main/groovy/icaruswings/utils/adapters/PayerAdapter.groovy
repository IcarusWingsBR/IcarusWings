package icaruswings.utils.adapters

import icaruswings.Customer
import icaruswings.utils.PersonType
import icaruswings.utils.validator.ValidateCpfCnpj
import icaruswings.utils.validator.CheckEntityExistenceById

class PayerAdapter {
    String id

    String name

    String email

    String cpfCnpj

    String postalCode

    String address

    String province

    String city

    String state

    String addressNumber

    String addressComplement

    Customer customer

    String phone

    PersonType personType

    public PayerAdapter(Map params) {
        if (!CheckEntityExistenceById.CheckCustomerExistenceById(params.customerId)) throw new RuntimeException("Cliente nao encontrado")

        this.id = params.id
        this.name = params.name
        this.email = params.email
        this.cpfCnpj = ValidateCpfCnpj.cleanCpfCnpj(params.cpfCnpj)
        this.postalCode = params.postalCode
        this.address = params.address
        this.province = params.province
        this.city = params.city
        this.state = params.state
        this.addressNumber = params.addressNumber
        this.addressComplement = params.addressComplement
        this.customer = Customer.get(params.customerId)
        this.phone = params.phone

        if(ValidateCpfCnpj.isCPF(params.cpfCnpj)) {
            this.personType = PersonType.NATURAL
        } else if (ValidateCpfCnpj.isCNPJ(params.cpfCnpj)){
            this.personType = PersonType.LEGAL
        }
    }
}