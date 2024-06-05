package icaruswings.utils.validator

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient
import icaruswings.utils.string.StringUtils
import io.micronaut.http.HttpStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PostalCodeValidator {

    private static final Logger log = LoggerFactory.getLogger(PostalCodeValidator.class)

    public static Boolean isValid(String postalCode) {
        if (!StringUtils.containsOnlyNumbers(postalCode)) return false

        if (!isValidOnViacep(postalCode)) return false

        return true
    }

    private static Boolean isValidOnViacep(String postalCode) {
        try {
            String apiUrl = "https://viacep.com.br/ws/${postalCode}/json/"
            RESTClient restClient = new RESTClient(apiUrl)
            HttpResponseDecorator apiResponse = restClient.get(path: '', contentType: groovyx.net.http.ContentType.JSON)

            if (apiResponse.status != HttpStatus.OK.code) return false

            if (apiResponse.data.erro) return false

            return true
        } catch (Exception exception) {
            log.error("Erro ao validar CEP: ${exception.message}", exception)

            return false
        }
    }
}