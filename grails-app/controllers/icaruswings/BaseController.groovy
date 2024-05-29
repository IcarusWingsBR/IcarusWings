package icaruswings

import grails.validation.ValidationException

abstract class BaseController {

    def handleValidationExceptionException(ValidationException exception) {
        log.error("Erro com os seguintes parâmetros ${params}", exception)

        flash.type = "error"
        flash.message = "Verifique os dados informados e tente novamente."

        redirect(action: "index", params: params)
    }

    def handleNumberFormatException(NumberFormatException exception) {
        log.error("Erro com os seguintes parâmetros ${params}", exception)

        flash.type = "error"
        flash.message = "Verifique se você preencheu todos os campos e tente novamente."

        redirect(action: "index", params: params)
    }

    def handleNullPointerException(NullPointerException exception) {
        log.error("Erro com os seguintes parâmetros ${params}", exception)

        flash.type = "error"
        flash.message = "Por favor, preencha todos os campos."

        redirect(action: "index", params: params)
    }

     def handleRuntimeException(RuntimeException exception) {
        log.error("Erro com os seguintes parâmetros ${params}", exception)

        flash.type = "error"
        flash.message = "Algo deu errado. Tente novamente."

        redirect(action: "index", params: params)
    }
}