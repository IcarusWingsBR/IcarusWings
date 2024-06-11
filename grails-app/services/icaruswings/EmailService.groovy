package icaruswings

import grails.gorm.transactions.Transactional
import java.text.SimpleDateFormat
import icaruswings.payment.Payment
import static grails.async.Promises.*

@Transactional
class EmailService {

    def mailService

    public void sendCreatePaymentEmailToPayer(Payer payer, Payment payment) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy")
        String formattedDueDate = dateFormat.format(payment.dueDate)

        task {
            mailService.sendMail {
                to payer.email
                subject "Criação de cobrança"
                body "Olá ${payer.name},\nUma cobrança com valor de R\$${payment.value} foi criada e tem você como pagador.\nPor favor, se atente a data de validade: ${formattedDueDate}.\n\nAtenciosamente,\nEquipe do Icarus Wings."
            }
        }
    }

    public void sendStatusChangeEmailToPayer(Payer payer, Payment payment) {
        task {
            mailService.sendMail {
                to payer.email
                subject "Status da sua cobrança foi alterado"
                body "Olá ${payer.name},\nO status da cobrança de número ${payment.id} foi alterado para: ${payment.paymentStatus}.\n\nAtenciosamente,\nIcarus Wings."
            }
        }
    }

    public void sendPaymentConfirmationEmailToPayed(Payer payer, Payment payment, Receipt receipt) {
        String token = receipt.token

        String emailBody = """
            <html>
                <body>
                    <p>Olá, ${payer.name} </p>
                    <p>Informamos que a cobrança ${payment.id} que estava em seu nome foi paga.</p>
                    <p><a href="http://localhost:8080/receipt/show/${token}">Clique aqui para visualizar o comprovante</a></p>
                    <p>Atenciosamente, Equipe do Icarus Wings</p>
                </body>
            <html>
        """

        task {
            mailService.sendMail {
                to payer.email
                subject "Pagamento de cobrança realizado"
                html emailBody
            }
        }
    }

    public void sendCreatePaymentEmailToCustomer(Payer payer, Payment payment) {
        Customer customer = payer.customer
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy")
        String formattedDueDate = dateFormat.format(payment.dueDate)

        task {
            mailService.sendMail {
                to customer.email
                subject "Criação de cobrança"
                body "Olá ${customer.name},\nSua cobrança com valor de R\$${payment.value} foi criada e o pagador é ${payer.name}.\n\nAtenciosamente,\nEquipe do Icarus Wings."
            }
        }
    }

    public void sendStatusChangeEmailToCustomer(Payer payer, Payment payment) {
        Customer customer = payer.customer
        
        task {
            mailService.sendMail {
                to customer.email
                subject "Status da sua cobrança foi alterado"
                body "Olá ${customer.name},\nO status da cobrança de número ${payment.id} foi alterado para: ${payment.paymentStatus}.\n\nAtenciosamente,\nIcarus Wings."
            }
        }
    }

    public void sendPaymentConfirmationEmailToCustomer(Payer payer, Payment payment, Receipt receipt) {
        Customer customer = payer.customer
        String token = receipt.token

        String emailBody = """
            <html>
                <body>
                    <p>Olá, ${customer.name} </p>
                    <p>Informamos que a cobrança ${payment.id} que estava no nome de ${payer.name} foi paga.</p>
                    <p><a href="http://localhost:8080/receipt/show/${token}">Clique aqui para visualizar o comprovante</a></p>
                    <p>Atenciosamente, Equipe do Icarus Wings</p>
                </body>
            <html>
        """

        task {
            mailService.sendMail {
                to customer.email
                subject "Pagamento de cobrança"
                html emailBody
            }
        }
    }
}