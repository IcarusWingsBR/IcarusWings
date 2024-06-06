package icaruswings

import icaruswings.repositories.ReceiptRepository

class ReceiptController extends BaseController {

    def show() {
        String token = params.token
   
        Receipt receipt = ReceiptRepository.query([token:token]).get()

        if (!receipt) render "Cobrança não encontrada."

        return [receipt: receipt]
    }
}