package icaruswings

import grails.gorm.transactions.Transactional
import icaruswings.utils.PersonType

@Transactional
class PayerService {
    public Payer save(Map parsedParams) {
        Payer payer = new Payer()

        payer.name = parsedParams.name

        payer.email = parsedParams.email

        payer.cpfCnpj = parsedParams.cpfCnpj

        payer.cep = parsedParams.cep

        payer.street = parsedParams.street

        payer.neighborhood = parsedParams.neighborhood

        payer.city = parsedParams.neighborhood

        payer.state = parsedParams.state

        payer.number = parsedParams.number

        payer.complement = parsedParams.complement

        payer.personType = PersonType.NATURAL

        payer.customer = Customer.get(parsedParams.customerId)

        payer.phoneNumber = parsedParams.phoneNumber

        payer.save(failOnError: true)

        return payer
    }
}