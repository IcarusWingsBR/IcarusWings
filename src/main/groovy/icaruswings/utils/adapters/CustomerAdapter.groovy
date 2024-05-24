package icaruswings.utils.adapters

import icaruswings.utils.PersonType
import icaruswings.utils.validator.ValidateCpfCnpj

class CustomerAdapter {

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

    String phoneNumber

    PersonType personType

    public CustomerAdapter(Map params) {
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
        this.phoneNumber = params.phoneNumber

        if(this.cpfCnpj && ValidateCpfCnpj.isCPF(params.cpfCnpj)) {
            this.personType = PersonType.NATURAL
        } else if (this.cpfCnpj && ValidateCpfCnpj.isCNPJ(params.cpfCnpj)) 
            this.personType = PersonType.LEGAL
        }
}