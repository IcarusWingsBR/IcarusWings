package icaruswings

import grails.plugin.springsecurity.annotation.Secured
import icaruswings.repositories.ReceiptRepository

@Secured(['permitAll'])
class ReceiptController extends BaseController {

    def show() {
        String token = params.id
   
        Receipt receipt = ReceiptRepository.query([token:token]).readOnly().get()

        if (!receipt) render "Comprovante não encontrado."

        return [receipt: receipt]
    }
}