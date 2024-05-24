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

        payer.postalCode = parsedParams.postalCode

        payer.address = parsedParams.address

        payer.province = parsedParams.province

        payer.city = parsedParams.city

        payer.state = parsedParams.state

        payer.addressNumber = Integer.parseInt(parsedParams.addressNumber)

        payer.addressComplement = parsedParams.addressComplement

        payer.personType = PersonType.NATURAL

        payer.customer = Customer.get(parsedParams.customerId)

        payer.phoneNumber = parsedParams.phoneNumber

        payer.save(failOnError: true)

        return payer
    }
}