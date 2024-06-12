package icaruswings.adapters

import icaruswings.Customer
import icaruswings.utils.PersonType
import icaruswings.utils.validator.CpfCnpjValidator

class PayerAdapter {
    Long id

    String name

    String email

    String cpfCnpj

    String postalCode

    String address

    String province

    String city

    String state

    Integer addressNumber

    String addressComplement

    Customer customer

    String phone

    PersonType personType

    public PayerAdapter(Customer customer, Map params) {
        if (params.id) this.id = Long.valueOf(params.id)

        this.name = params.name
        this.email = params.email
        this.cpfCnpj = CpfCnpjValidator.cleanCpfCnpj(params.cpfCnpj)
        this.postalCode = params.postalCode
        this.address = params.address
        this.province = params.province
        this.city = params.city
        this.state = params.state
        this.addressNumber = Integer.parseInt(params.addressNumber)
        this.addressComplement = params.addressComplement
        this.customer = customer
        this.phone = params.phone

        if (CpfCnpjValidator.isCPF(params.cpfCnpj)) {
            this.personType = PersonType.NATURAL
        } else if (CpfCnpjValidator.isCNPJ(params.cpfCnpj)){
            this.personType = PersonType.LEGAL
        }
    }
}