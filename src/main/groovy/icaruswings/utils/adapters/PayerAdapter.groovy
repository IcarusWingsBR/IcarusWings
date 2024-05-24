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

    String phone

    public PayerAdapter(Map params) {
        this.id = params.id
        
        this.name = params.name

        this.email = params.email

        this.cpfCnpj = params.cpfCnpj

        this.postalCode = params.postalCode

        this.address = params.address

        this.province = params.province

        this.city = params.city

        this.state = params.state

        this.addressNumber = params.addressNumber

        this.addressComplement = params.addressComplement

        this.customer = Customer.get(params.customerId)

        this.phone = params.phone
    }
}