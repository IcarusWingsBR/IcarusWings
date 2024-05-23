package icaruswings.utils.adapters

import icaruswings.Customer

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

    String phoneNumber

    public PayerAdapter(Map params) {
        this.id = params.id
        
        this.name = params.name

        this.email = params.email

        this.cpfCnpj = params.cpfCnpj

        this.postalCode = params.cep

        this.address = params.street

        this.province = params.neighborhood

        this.city = params.city

        this.state = params.state

        this.addressNumber = params.number

        this.addressComplement = params.complement

        this.customer = Customer.get(params.customerId)

        this.phoneNumber = params.phoneNumber
    }
}