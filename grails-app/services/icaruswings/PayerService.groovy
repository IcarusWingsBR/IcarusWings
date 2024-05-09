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
        payer.customer = Customer.get(parsedParams.customerId)
        payer.personType = PersonType.NATURAL
        payer.save(failOnError: true)
        return payer
    }
}