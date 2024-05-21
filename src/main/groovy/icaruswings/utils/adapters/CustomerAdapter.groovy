package icaruswings.utils.adapters

class CustomerAdapter {

    String id

    String name

    String email

    String cpfCnpj

    String cep

    String street

    String neighborhood

    String city

    String state

    String number

    String complement

    public CustomerAdapter(Map params) {
        this.id = params.id

        this.name = params.name

        this.email = params.email

        this.cpfCnpj = params.cpfCnpj

        this.cep = params.cep

        this.street = params.street

        this.neighborhood = params.neighborhood

        this.city = params.city

        this.state = params.state

        this.number = params.number

        this.complement = params.complement
    }
}