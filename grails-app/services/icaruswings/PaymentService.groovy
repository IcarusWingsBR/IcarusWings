package icaruswings

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import icaruswings.utils.adapters.PaymentAdapter
import icaruswings.utils.validator.ValueValidator
import icaruswings.utils.validator.DateValidator

@Transactional
class PaymentService {

    public Payment save(PaymentAdapter paymentAdapter) {
        Payment validatedPayment= validateSave(paymentAdapter)

        if (validatedPayment.hasErrors()) {
            throw new ValidationException("Não foi possível salvar a cobrança", validatedPayment.errors)
        }

        Payment payment = new Payment()

        payment.payer = paymentAdapter.payer

        payment.paymentType = paymentAdapter.paymentType

        payment.paymentStatus = paymentAdapter.paymentStatus

        payment.value = parseValueToDouble(paymentAdapter.value)

        payment.dueDate = paymentAdapter.dueDate

        payment.save(failOnError: true)

        return payment
    }

    public void update(PaymentAdapter paymentAdapter) {
        Long id = Long.parseLong(paymentAdapter.id)
        Payment payment = Payment.get(id)

        Payment validatedPayment = validateSave(paymentAdapter)

        if (!payment || payment.deleted) throw new RuntimeException("Customer não encontrado")

        if (validatedPayment.hasErrors()) {
            throw new ValidationException("Não foi possível salvar o cliente", validatedPayment.errors)
        }

        payment.payer = paymentAdapter.payer

        payment.paymentType = paymentAdapter.paymentType

        payment.value = parseValueToDouble(paymentAdapter.value)

        payment.dueDate = paymentAdapter.dueDate

        payment.save(failOnError: true)
    }

    private Payment validateSave(PaymentAdapter paymentAdapter) {
        Payment payment = new Payment()

        if (!paymentAdapter.paymentType) {
            payment.errors.rejectValue("paymentType", null, "O tipo de pagamento é inválido")
        }

        if (!paymentAdapter.value) {
            payment.errors.rejectValue("value", null, "O campo valor é obrigatório")
        } else if (!ValueValidator.isValid(paymentAdapter.value)) {
            payment.errors.rejectValue("value", null, "O valor informado é inválido")
        }

        if (!paymentAdapter.dueDate) {
            payment.errors.rejectValue("dueDate",  null, "O campo data de vencimento é obrigatório")
        } else if (!DateValidator.isValid(paymentAdapter.dueDate)) {
            payment.errors.rejectValue("dueDate",  null, "A data informada é inválida")
        }

        return payment
    }

    private Double parseValueToDouble(String stringValue) {
        String valueWithDote = stringValue.replaceAll( "," , "." )
        Double value = Double.parseDouble(valueWithDote)

        return value
    }
}
