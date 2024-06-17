package icaruswings

import grails.plugin.springsecurity.annotation.Secured
import icaruswings.repositories.ReceiptRepository

class ReceiptController extends BaseController {

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def show() {
        String token = params.id
   
        Receipt receipt = ReceiptRepository.query([token:token]).readOnly().get()

        if (!receipt) render "Comprovante não encontrado."

        return [receipt: receipt]
    }
}