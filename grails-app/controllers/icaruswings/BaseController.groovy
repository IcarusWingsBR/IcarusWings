package icaruswings

import grails.validation.ValidationException

abstract class BaseController {

    def handleValidationExceptionException(ValidationException exception) {
        log.error("Erro com os seguintes parâmetros ${params}", exception)

        flash.type = "error"
        flash.message = "Verifique os dados informados e tente novamente."

        redirect(action: "index", params: params)
    }
}