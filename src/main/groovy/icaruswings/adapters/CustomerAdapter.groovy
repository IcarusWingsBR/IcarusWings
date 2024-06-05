package icaruswings.adapters

import icaruswings.utils.PersonType
import icaruswings.utils.validator.CpfCnpjValidator

class CustomerAdapter {

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

    String phone

    PersonType personType

    public CustomerAdapter(Map params) {
        this.name = params.name
        this.email = params.email
        this.postalCode = params.postalCode
        this.address = params.address
        this.province = params.province
        this.city = params.city
        this.state = params.state
        this.addressNumber = Integer.parseInt(params.addressNumber)
        this.addressComplement = params.addressComplement
        this.phone = params.phone

        if (params.id) this.id = Long.valueOf(params.id)

        if (!params.cpfCnpj) return

        this.cpfCnpj = CpfCnpjValidator.cleanCpfCnpj(params.cpfCnpj)
  
        if (CpfCnpjValidator.isCPF(params.cpfCnpj)) {
            this.personType = PersonType.NATURAL
        } else if (CpfCnpjValidator.isCNPJ(params.cpfCnpj)) {
            this.personType = PersonType.LEGAL
        }
    }
}