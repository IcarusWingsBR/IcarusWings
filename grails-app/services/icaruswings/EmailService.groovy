package icaruswings

import grails.gorm.transactions.Transactional

@Transactional
class EmailService {

    def mailService

    def sendStatusChangeEmail(Payer payer, Payment payment) {
        mailService.sendMail {
            to payer.email
            subject "Status da sua cobrança foi alterado"
            body "Olá ${payer.name},\nO status da cobrança de número ${payment.id} foi alterado para: ${payment.paymentStatus}.\n\nAtenciosamente,\nEquipe."
        }
    }
}