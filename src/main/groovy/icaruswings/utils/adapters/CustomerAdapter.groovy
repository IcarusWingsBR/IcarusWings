package icaruswings.utils.adapters

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
    }
}