package icaruswings

import grails.gorm.transactions.Transactional
import grails.compiler.GrailsCompileStatic
import icaruswings.payment.Payment
import icaruswings.notification.CustomerNotification
import icaruswings.notification.CustomerNotificationType
import grails.core.GrailsApplication
import icaruswings.repositories.CustomerNotificationRepository

@GrailsCompileStatic
@Transactional
class CustomerNotificationService {

    GrailsApplication grailsApplication

    public void savePaymentCreatedNotification(Payment payment) {
        CustomerNotification customerNotification = new CustomerNotification()
        String baseUrl = grailsApplication.config.getProperty('baseUrl')

        customerNotification.title = "Criação de cobrança."
        customerNotification.message = "Sua cobrança de número ${payment.id} foi criada com sucesso."
        customerNotification.url = baseUrl + "payment/${payment.id}/show"
        customerNotification.type = CustomerNotificationType.PAYMENT_CREATED
        customerNotification.customer = payment.payer.customer
        customerNotification.save(failOnError: true)
    }

    public void savePaymentPaidNotification(Payment payment) {
        CustomerNotification customerNotification = new CustomerNotification()
        String baseUrl = grailsApplication.config.getProperty('baseUrl')

        customerNotification.title = "Cobrança Paga."
        customerNotification.message = "Sua cobrança de número ${payment.id} foi paga por ${payment.payer.name}."
        customerNotification.url = baseUrl + "payment/${payment.id}/show"
        customerNotification.type = CustomerNotificationType.PAYMENT_PAID
        customerNotification.customer = payment.payer.customer
        customerNotification.save(failOnError: true)
    }

    public void savePaymentOverdueNotification(Payment payment) {
        CustomerNotification customerNotification = new CustomerNotification()
        String baseUrl = grailsApplication.config.getProperty('baseUrl')

        customerNotification.title = "Cobrança Vencida."
        customerNotification.message = "Sua cobrança de número ${payment.id} está vencida."
        customerNotification.url = baseUrl + "payment/${payment.id}/show"
        customerNotification.type = CustomerNotificationType.PAYMENT_OVERDUE
        customerNotification.customer = payment.payer.customer
        customerNotification.save(failOnError: true)
    }

    public void savePaymentDeletedNotification(Payment payment) {
        CustomerNotification customerNotification = new CustomerNotification()
        String baseUrl = grailsApplication.config.getProperty('baseUrl')

        customerNotification.title = "Cobrança Excluída."
        customerNotification.message = "Sua cobrança de número ${payment.id} foi excluída com sucesso."
        customerNotification.url = baseUrl + "payment/${payment.id}/show"
        customerNotification.type = CustomerNotificationType.PAYMENT_DELETED
        customerNotification.customer = payment.payer.customer
        customerNotification.save(failOnError: true)
    }

    public void savePaymentUpdatedNotification(Payment payment) {
        CustomerNotification customerNotification = new CustomerNotification()
        String baseUrl = grailsApplication.config.getProperty('baseUrl')

        customerNotification.title = "Cobrança Atualizada."
        customerNotification.message = "Sua cobrança de número ${payment.id} foi atualizada com sucesso."
        customerNotification.url = baseUrl + "payment/${payment.id}/show"
        customerNotification.type = CustomerNotificationType.PAYMENT_UPDATED
        customerNotification.customer = payment.payer.customer
        customerNotification.save(failOnError: true)
    }

    public void savePaymentRestoredNotification(Payment payment) {
        CustomerNotification customerNotification = new CustomerNotification()
        String baseUrl = grailsApplication.config.getProperty('baseUrl')

        customerNotification.title = "Cobrança Restaurada."
        customerNotification.message = "Sua cobrança de número ${payment.id} foi restaurada com sucesso."
        customerNotification.url = baseUrl + "payment/${payment.id}/show"
        customerNotification.type = CustomerNotificationType.PAYMENT_RESTORED
        customerNotification.customer = payment.payer.customer
        customerNotification.save(failOnError: true)
    }

    public List<CustomerNotification> list(Long customerId) {
        return CustomerNotificationRepository.query([customerId:customerId]).readOnly().list()
    }
}