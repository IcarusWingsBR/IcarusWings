package icaruswings

import grails.validation.ValidationException

abstract class BaseController {

    def handleValidationExceptionException(ValidationException exception) {
        String message =  exception.getErrors().getAllErrors()
            .collect { it.getDefaultMessage() }
            .join("\n")

        log.error("Erro com os seguintes parâmetros ${params}", exception)

        flash.type = "error"
        flash.message = message

        redirect(action: "index", params: params)
    }

    def handleException(Exception exception) {
        log.error("Erro com os seguintes parâmetros ${params}", exception)

        flash.type = "error"
        flash.message = "Algo deu errado. Tente novamente."

        redirect(action: "index", params: params)
    }

    def getCurrentUser() {
        return (getAuthenticatedUser() as User)
    }

    def getCurrentCustomer() {
        return (getAuthenticatedUser() as User).customer
    }

    def getCurrentCustomerId() {
        return (getAuthenticatedUser() as User).customerId
    }
}