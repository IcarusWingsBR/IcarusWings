package icaruswings

import grails.validation.ValidationException
import icaruswings.utils.exceptions.BusinessException

abstract class BaseController {

    def handleValidationExceptionException(ValidationException exception) {
        String message =  exception.getErrors().getAllErrors()
            .collect { it.getDefaultMessage() }
            .join("\n")

        log.error("Erro com os seguintes parâmetros ${params}", exception)

        flash.type = "error"
        flash.message = message
        
        if(isLoggedIn()) {
            redirect(action: "index", params: params)
        } else {
            redirect(action: "createCustomer", controller: "login")
        }
    }

    def BusinessException(BusinessException exception) {
        log.error("Erro com os seguintes parâmetros ${params}", exception)

        flash.type = "error"
        flash.message = exception.message

        redirect(action: "index", params: params)
    }

    def handleException(Exception exception) {
        log.error("Erro com os seguintes parâmetros ${params}", exception)

        flash.type = "error"
        flash.message = "Algo deu errado. Tente novamente."

        redirect(action: "index", params: params)
    }

    def getCurrentCustomer() {
        return (getAuthenticatedUser() as User).customer
    }

    def getCurrentCustomerId() {
        return (getAuthenticatedUser() as User).customerId
    }
}