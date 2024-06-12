package icaruswings

import grails.gorm.transactions.Transactional
import icaruswings.payment.Payment
import icaruswings.notification.Notification
import icaruswings.notification.NotificationType

@Transactional
class NotificationService {

    public void createPaymentCreatedNotification(Payment payment) {
        Notification notification = new Notification()

        notification.title = "Criação de cobrança."
        notification.message = "Sua cobrança de número ${payment.id} foi criada com sucesso."
        notification.url = "localhost:8080/payment/${payment.id}/show"
        notification.type = NotificationType.CREATED
        notification.customer = payment.payer.customer
        notification.save(failOnError: true)
    }

    public void createPaymentPayedNotification(Payment payment) {
        Notification notification = new Notification()

        notification.title = "Cobrança Paga."
        notification.message = "Sua cobrança de número ${payment.id} foi paga por ${payment.payer.name}."
        notification.url = "localhost:8080/payment/${payment.id}/show"
        notification.type = NotificationType.PAYED
        notification.customer = payment.payer.customer
        notification.save(failOnError: true)
    }

    public void createPaymentOverdueNotification(Payment payment) {
        Notification notification = new Notification()

        notification.title = "Cobrança Vencida."
        notification.message = "Sua cobrança de número ${payment.id} está vencida."
        notification.url = "localhost:8080/payment/${payment.id}/show"
        notification.type = NotificationType.OVERDUE
        notification.customer = payment.payer.customer
        notification.save(failOnError: true)
    }

    public void createPaymentDeletedNotification(Payment payment) {
        Notification notification = new Notification()

        notification.title = "Cobrança Excluída."
        notification.message = "Sua cobrança de número ${payment.id} foi excluída com sucesso."
        notification.url = "localhost:8080/payment/${payment.id}/show"
        notification.type = NotificationType.DELETED
        notification.customer = payment.payer.customer
        notification.save(failOnError: true)
    }

    public void createPaymentUpdatedNotification(Payment payment) {
        Notification notification = new Notification()

        notification.title = "Cobrança Atualizada."
        notification.message = "Sua cobrança de número ${payment.id} foi atualizada com sucesso."
        notification.url = "localhost:8080/payment/${payment.id}/show"
        notification.type = NotificationType.UPDATED
        notification.customer = payment.payer.customer
        notification.save(failOnError: true)
    }

    public void createPaymentRestoredNotification(Payment payment) {
        Notification notification = new Notification()

        notification.title = "Cobrança Restaurada."
        notification.message = "Sua cobrança de número ${payment.id} foi restaurada com sucesso."
        notification.url = "localhost:8080/payment/${payment.id}/show"
        notification.type = NotificationType.RESTORED
        notification.customer = payment.payer.customer
        notification.save(failOnError: true)
    }
}