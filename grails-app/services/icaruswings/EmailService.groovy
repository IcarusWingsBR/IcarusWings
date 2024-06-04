package icaruswings

import grails.gorm.transactions.Transactional
import java.text.SimpleDateFormat

@Transactional
class EmailService {

    def mailService

    def sendCreatePaymentEmailToPayer(Payer payer, Payment payment) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy")
        String formattedDueDate = dateFormat.format(payment.dueDate)

        mailService.sendMail {
            to payer.email
            subject "Criação de cobrança"
            body "Olá ${payer.name},\nUma cobrança com valor de R\$${payment.value} foi criada e tem você como pagador.\nPor favor, se atente a data de validade: ${formattedDueDate}.\n\nAtenciosamente,\nEquipe do Icarus Wings."
        }
    }

    def sendStatusChangeEmailToPayer(Payer payer, Payment payment) {
        mailService.sendMail {
            to payer.email
            subject "Status da sua cobrança foi alterado"
            body "Olá ${payer.name},\nO status da cobrança de número ${payment.id} foi alterado para: ${payment.paymentStatus}.\n\nAtenciosamente,\nIcarus Wings."
        }
    }

    def sendCreatePaymentEmailToCustomer(Payer payer, Payment payment) {
        Customer customer = payer.customer
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy")
        String formattedDueDate = dateFormat.format(payment.dueDate)

        mailService.sendMail {
            to customer.email
            subject "Criação de cobrança"
            body "Olá ${customer.name},\nSua cobrança com valor de R\$${payment.value} foi criada e o pagador é ${payer.name}.\n\nAtenciosamente,\nEquipe do Icarus Wings."
        }
    }

    def sendStatusChangeEmailToCustomer(Payer payer, Payment payment) {
        Customer customer = payer.customer
        mailService.sendMail {
            to customer.email
            subject "Status da sua cobrança foi alterado"
            body "Olá ${customer.name},\nO status da cobrança de número ${payment.id} foi alterado para: ${payment.paymentStatus}.\n\nAtenciosamente,\nIcarus Wings."
        }
    }
}