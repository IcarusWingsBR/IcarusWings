package icaruswings.utils.validator

import icaruswings.Customer
import icaruswings.Payer
import icaruswings.Payment

class CheckEntityExistenceById {

    public static Boolean CheckCustomerExistenceById(String idString) {
        Long id = Long.valueOf(idString)
        Customer customer = Customer.get(id)

        if (!customer || customer.deleted) return false

        return true
    }
    public static Boolean CheckPayerExistenceById(String idString) {
        Long id = Long.valueOf(idString)
        Payer payer = Payer.get(id)

        if (!payer || payer.deleted) return false

        return true
    }

    public static Boolean CheckPaymentExistenceById(String idString) {
        Long id = Long.valueOf(idString)
        Payment payment = Payment.get(id)

        if (!payment || payment.deleted) return false

        return true
    }
}