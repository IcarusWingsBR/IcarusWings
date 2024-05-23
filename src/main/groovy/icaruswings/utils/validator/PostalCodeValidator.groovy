package icaruswings.utils.validator

import groovyx.net.http.RESTClient

class PostalCodeValidator {

    public static Boolean isValidCep(String cep) {
        if (!StringUtils.containsOnlyNumbers(cep)) return false

        if (!ValidateExistingCep(cep)) return false

        return true
    }

    public static Boolean ValidateExistingCep(String cep) {
        def apiUrl = "https://viacep.com.br/ws/${cep}/json/"
        def restClient = new RESTClient(apiUrl)
        def response
        
        try {
            response = restClient.get(path: '', contentType: groovyx.net.http.ContentType.JSON)
        } catch (Exception e) {
            throw new RuntimeException("Erro ao validar CEP: ${e.message}", e)

            return false
        }

        if (response.status != 200) return false
        
        if (response.data.erro) return false

        return true
    }
}