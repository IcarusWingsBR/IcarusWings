package icaruswings

import grails.gorm.transactions.Transactional
import grails.compiler.GrailsCompileStatic
import icaruswings.payment.Payment
import icaruswings.notification.CustomerNotification
import icaruswings.notification.CustomerNotificationType

@GrailsCompileStatic
@Transactional
class CustomerNotificationService {

    public void savePaymentCreatedNotification(Payment payment) {
        CustomerNotification customerNotification = new CustomerNotification()

        customerNotification.title = "Criação de cobrança."
        customerNotification.message = "Sua cobrança de número ${payment.id} foi criada com sucesso."
        customerNotification.url = "http://localhost:8080/payment/${payment.id}/show"
        customerNotification.type = CustomerNotificationType.PAYMENT_CREATED
        customerNotification.customer = payment.payer.customer
        customerNotification.save(failOnError: true)
    }

    public void savePaymentPaidNotification(Payment payment) {
        CustomerNotification customerNotification = new CustomerNotification()

        customerNotification.title = "Cobrança Paga."
        customerNotification.message = "Sua cobrança de número ${payment.id} foi paga por ${payment.payer.name}."
        customerNotification.url = "http://localhost:8080/payment/${payment.id}/show"
        customerNotification.type = CustomerNotificationType.PAYMENT_PAYED
        customerNotification.customer = payment.payer.customer
        customerNotification.save(failOnError: true)
    }

    public void savePaymentOverdueNotification(Payment payment) {
        CustomerNotification customerNotification = new CustomerNotification()

        customerNotification.title = "Cobrança Vencida."
        customerNotification.message = "Sua cobrança de número ${payment.id} está vencida."
        customerNotification.url = "http://localhost:8080/payment/${payment.id}/show"
        customerNotification.type = CustomerNotificationType.PAYMENT_OVERDUE
        customerNotification.customer = payment.payer.customer
        customerNotification.save(failOnError: true)
    }

    public void savePaymentDeletedNotification(Payment payment) {
        CustomerNotification customerNotification = new CustomerNotification()

        customerNotification.title = "Cobrança Excluída."
        customerNotification.message = "Sua cobrança de número ${payment.id} foi excluída com sucesso."
        customerNotification.url = "http://localhost:8080/payment/${payment.id}/show"
        customerNotification.type = CustomerNotificationType.PAYMENT_DELETED
        customerNotification.customer = payment.payer.customer
        customerNotification.save(failOnError: true)
    }

    public void savePaymentUpdatedNotification(Payment payment) {
        CustomerNotification customerNotification = new CustomerNotification()

        customerNotification.title = "Cobrança Atualizada."
        customerNotification.message = "Sua cobrança de número ${payment.id} foi atualizada com sucesso."
        customerNotification.url = "http://localhost:8080/payment/${payment.id}/show"
        customerNotification.type = CustomerNotificationType.PAYMENT_UPDATED
        customerNotification.customer = payment.payer.customer
        customerNotification.save(failOnError: true)
    }

    public void savePaymentRestoredNotification(Payment payment) {
        CustomerNotification customerNotification = new CustomerNotification()

        customerNotification.title = "Cobrança Restaurada."
        customerNotification.message = "Sua cobrança de número ${payment.id} foi restaurada com sucesso."
        customerNotification.url = "http://localhost:8080/payment/${payment.id}/show"
        customerNotification.type = CustomerNotificationType.PAYMENT_RESTORED
        customerNotification.customer = payment.payer.customer
        customerNotification.save(failOnError: true)
    }
}