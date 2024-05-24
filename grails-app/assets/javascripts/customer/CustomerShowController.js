function CustomerShowController() {
    this.reference = document.querySelector(".background-box")
    var _this = this;
    var postalCodeValidator

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
        _this.reference.querySelector('#address').value = ("");
        _this.reference.querySelector('#province').value = ("");
        _this.reference.querySelector('#city').value = ("");
        _this.reference.querySelector('#state').value = ("");
    };

    this.setInputValuesToPending = function() {
        _this.reference.querySelector('#address').value = "Aguardando";
        _this.reference.querySelector('#province').value = "Aguardando";
        _this.reference.querySelector('#city').value = "Aguardando";
        _this.reference.querySelector('#state').value = "Aguardando";
    };

    this.processPostalCodeResponse = function(response) {
        if (!postalCodeValidator.isContentValid(response)) {
            _this.clearPostalCodeFormat();

            return;
        }

        _this.updateAddressFields(response);
    };

    this.updateAddressFields = function(response) {
        _this.reference.querySelector('#address').value = (response.logradouro);
        _this.reference.querySelector('#province').value = (response.bairro);
        _this.reference.querySelector('#city').value = (response.localidade);
        _this.reference.querySelector('#state').value = (response.uf);

        _this.disableInputs();
    };

    this.disableInputs = function() {
        _this.reference.querySelector('#address').readOnly = true;
        _this.reference.querySelector('#province').readOnly = true;
        _this.reference.querySelector('#city').readOnly = true;
        _this.reference.querySelector('#state').readOnly = true;
    };
}

var customerShowController;

document.addEventListener("DOMContentLoaded", () => {
    customerShowController = new CustomerShowController();
    customerShowController.init();
})
