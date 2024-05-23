package icaruswings.utils.validator

import groovyx.net.http.RESTClient

class PostalCodeValidator {

    public static Boolean isValid(String postalCode) {
        if (!StringUtils.containsOnlyNumbers(postalCode)) return false

        if (!isValidOnViacep(postalCode)) return false

        return true
    }

    private static Boolean isValidOnViacep(String postalCode) {
        def apiUrl = "https://viacep.com.br/ws/${postalCode}/json/"
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