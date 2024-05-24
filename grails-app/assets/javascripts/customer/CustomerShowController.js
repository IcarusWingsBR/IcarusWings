function CustomerShowController() {
    this.reference = document.querySelector(".background-box")
    var _this = this;
    var postalCodeValidator
    var addressReference = _this.reference.querySelector('#address')
    var provinceReference = _this.reference.querySelector('#province')
    var cityReference = _this.reference.querySelector('#city')
    var stateReference = _this.reference.querySelector('#state')
    
    this.init = function() {
        postalCodeValidator = new PostalCodeValidator();
        document.getElementById("postalCode").addEventListener("blur", _this.processPostalCodeInput);
    };

    this.processPostalCodeInput = async function(event) {
        const postalCode = event.target.value

        if (postalCodeValidator.isPostalCodeEmpty(postalCode)) {
            _this.clearPostalCodeFormat();

            return;
        }

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
        addressReference.value = (response.logradouro);
        provinceReference.value = (response.bairro);
        cityReference.value = (response.localidade);
        stateReference.value = (response.uf);

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
