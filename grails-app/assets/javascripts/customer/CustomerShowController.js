function CustomerShowController() {
    this.reference = document.getElementsByClassName("js-save-customer-form")[0]
    var _this = this;
    var postalCodeValidator
    var postalCodeReference = _this.reference.getElementsByClassName("js-postalCode")[0];
    var addressReference = _this.reference.getElementsByClassName('js-address')[0];
    var provinceReference = _this.reference.getElementsByClassName('js-province')[0];
    var cityReference = _this.reference.getElementsByClassName('js-city')[0];
    var stateReference = _this.reference.getElementsByClassName('js-state')[0];
    
    this.init = function() {
        postalCodeValidator = new PostalCodeValidator();
        postalCodeReference.addEventListener("blur", _this.processPostalCodeInput);
    };

    this.processPostalCodeInput = async function() {
        const postalCode = postalCodeReference.value

        const postalCodeSanitized = postalCode.replace(/\D/g, '');
    
        if (!postalCodeValidator.validatePostalCodeFormat(postalCodeSanitized)) {
            _this.clearPostalCodeFormat();

            return;
        }

        _this.setInputValuesToPending()

        postalCodeValidator.fetchPostalCode(postalCodeSanitized)
            .then(response => {
                _this.processPostalCodeResponse(response);
            })
            .catch(() => {
                _this.clearPostalCodeFormat();

                return;
            });
    }

    this.clearPostalCodeFormat = function() {
        addressReference.value = ("");
        provinceReference.value = ("");
        cityReference.value = ("");
        stateReference.value = ("");
    };

    this.setInputValuesToPending = function() {
        addressReference.value = "Aguardando";
        provinceReference.value = "Aguardando";
        cityReference.value = "Aguardando";
        stateReference.value = "Aguardando";
    };

    this.processPostalCodeResponse = function(response) {
        if (!postalCodeValidator.isContentValid(response)) {
            _this.clearPostalCodeFormat();

            return;
        }

        _this.updateAddressFields(response);
    };

    this.updateAddressFields = function(response) {
        addressReference.value = response.logradouro;
        provinceReference.value = response.bairro;
        cityReference.value = response.localidade;
        stateReference.value = response.uf;

        _this.disableInputs();
    };

    this.disableInputs = function() {
        addressReference.readOnly = true;
        provinceReference.readOnly = true;
        cityReference.readOnly = true;
        stateReference.readOnly = true;
    };
}

var customerShowController;

document.addEventListener("DOMContentLoaded", () => {
    customerShowController = new CustomerShowController();
    customerShowController.init();
})