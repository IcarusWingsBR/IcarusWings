package icaruswings

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import icaruswings.payment.Payment
import icaruswings.adapters.PaymentAdapter
import icaruswings.payment.PaymentStatus
import icaruswings.repositories.PaymentRepository
import icaruswings.utils.date.DateUtils

@Transactional
class PaymentService {

    def emailService

    public Payment save(PaymentAdapter paymentAdapter) {
        Payment validatedPayment = validateSave(paymentAdapter)

        if (validatedPayment.hasErrors()) throw new ValidationException("Não foi possível salvar a cobrança", validatedPayment.errors)

        Payment payment = new Payment()
        payment.payer = paymentAdapter.payer
        payment.paymentType = paymentAdapter .paymentType
        payment.paymentStatus = paymentAdapter.paymentStatus
        payment.value = paymentAdapter.value
        payment.dueDate = paymentAdapter.dueDate

        emailService.sendCreatePaymentEmailToPayer(payment.payer, payment)
        emailService.sendCreatePaymentEmailToCustomer(payment.payer, payment)

        payment.save(failOnError: true)

        return payment
    }

    public void update(PaymentAdapter paymentAdapter) {
        Payment validatedPayment = validateSave(paymentAdapter)

        if (validatedPayment.hasErrors()) throw new ValidationException("Não foi editar a cobrança", validatedPayment.errors)

        Payment payment = PaymentRepository.get(paymentAdapter.id)
        payment.payer = paymentAdapter.payer
        payment.paymentType = paymentAdapter.paymentType
        payment.value = paymentAdapter.value
        payment.dueDate = paymentAdapter.dueDate
        payment.save(failOnError: true)
    }

    public List<Payment> list() {
        return PaymentRepository.query([:]).list()
    }

    public void delete(Long id) {
        Payment payment = PaymentRepository.get(id)

        if (!payment) throw new RuntimeException("Essa cobrança não existe")

        payment.deleted = true

        payment.save(failOnError: true)
    }

    public void overduePayment(Long id) {
        Payment payment = PaymentRepository.get(id)

        if (!payment) throw new RuntimeException("Essa cobrança não existe")

        payment.paymentStatus = PaymentStatus.OVERDUE

        payment.save(failOnError: true)
    }

    public void processOverduePayments() {
        List<Long> overduePaymentsList = PaymentRepository.query([
                paymentStatus: PaymentStatus.PENDING,
                "dueDate[lt]": DateUtils.removeTime(new Date())
        ]).column("id").list() as List<Long>

        for (Long paymentId : overduePaymentsList) {
            Payment.withNewTransaction { status ->
                try {
                    overduePayment(paymentId)
                } catch (Exception exception) {
                    log.info("expireOverduePayments >> Erro ao atualizar status da cobrança de id: [${paymentId}] [Mensagem de erro]: ${exception.message}")
                }
            }
        }
    }

    public void confirmPaymentReceived(Long id) {
        Payment payment = PaymentRepository.get(id)

        if (!payment) throw new RuntimeException("Essa cobrança não existe")

        if(payment.paymentStatus != PaymentStatus.PENDING) throw new RuntimeException("Não foi possível realizar essa ação")

        payment.paymentStatus = PaymentStatus.PAYED

        payment.save(failOnError: true)
    }

    private Payment validateSave(PaymentAdapter paymentAdapter) {
        Payment payment = new Payment()

        if (!paymentAdapter.paymentType) {
            payment.errors.rejectValue("paymentType", null, "O tipo de pagamento é inválido")
        }

        if (!paymentAdapter.value) {
            payment.errors.rejectValue("value", null, "O campo valor é obrigatório")
        } else if (paymentAdapter.value < 0) {
            payment.errors.rejectValue("value", null, "O valor informado deve ser positivo")
        }

        if (!paymentAdapter.dueDate) {
            payment.errors.rejectValue("dueDate",  null, "O campo data de vencimento é obrigatório")
        } else if (DateUtils.isBeforeToday(paymentAdapter.dueDate)) {
            payment.errors.rejectValue("dueDate",  null, "A data informada é inválida")
        }

        return payment
    }
}
