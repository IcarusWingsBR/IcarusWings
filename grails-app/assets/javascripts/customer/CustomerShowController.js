function CustomerShowController() {
        var _this = this;
        var postalCodeValidator

        this.init = function () {
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
            document.getElementById('address').value = ("");
            document.getElementById('province').value = ("");
            document.getElementById('city').value = ("");
            document.getElementById('state').value = ("");
        };

        this.setInputValuesToPending = function() {
            document.getElementById('address').value = "Aguardando";
            document.getElementById('province').value = "Aguardando";
            document.getElementById('city').value = "Aguardando";
            document.getElementById('state').value = "Aguardando";
        };

        this.processPostalCodeResponse = function(response) {
            if (!postalCodeValidator.isContentValid(response)) {
                _this.clearPostalCodeFormat();

                return;
            }

            _this.updateAddressFields(response);
        };

        this.updateAddressFields = function(response) {
            document.getElementById('address').value = (response.logradouro);
            document.getElementById('province').value = (response.bairro);
            document.getElementById('city').value = (response.localidade);
            document.getElementById('state').value = (response.uf);

            _this.disableInputs();
        };

        this.disableInputs = function() {
            document.getElementById('address').readOnly = true;
            document.getElementById('province').readOnly = true;
            document.getElementById('city').readOnly = true;
            document.getElementById('state').readOnly = true;
        };
}

var customerShowController;

document.addEventListener("DOMContentLoaded", () => {
    customerShowController = new CustomerShowController();
    customerShowController.init();
})





