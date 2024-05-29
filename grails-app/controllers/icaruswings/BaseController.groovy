package icaruswings

import grails.validation.ValidationException

abstract class BaseController {

    def handleValidationExceptionException(ValidationException exception) {
        log.error("CustomerController.save >> Erro ao criar customer ${params}", exception)
        
        flash.type = "error"
        flash.message = "Verifique os dados informados e tente novamente."

        redirect(action: "index", params: params)
    }
}