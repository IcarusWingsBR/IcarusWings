package icaruswings

import grails.gorm.transactions.Transactional
import java.text.SimpleDateFormat

@Transactional
class EmailService {

    def mailService

    def sendStatusChangeEmail(Payer payer, Payment payment) {
        mailService.sendMail {
            to payer.email
            subject "Status da sua cobrança foi alterado"
            body "Olá ${payer.name},\nO status da cobrança de número ${payment.id} foi alterado para: ${payment.paymentStatus}.\n\nAtenciosamente,\nIcarus Wings."
        }
    }

    def sendCreatePaymentEmail(Payer payer, Payment payment) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy")
        String formattedDueDate = dateFormat.format(payment.dueDate)

        mailService.sendMail {
            to payer.email
            subject "Criação de cobrança"
            body "Olá ${payer.name},\nUma cobrança com valor de R\$${payment.value} foi criada e tem você como pagador.\nPor favor, se atente a data de validade: ${formattedDueDate}.\n\nAtenciosamente,\nEquipe do Icarus Wings."
        }
    }
}