function PaymentShowController() {
    this.reference = document.querySelector(".js-edit-payment-form");
    var _this = this;
    var payerReference = _this.reference.querySelector(".js-payer");
    var payerEmailReference = _this.reference.querySelector('.js-payer-email');
    var payerCpfCnpjReference = _this.reference.querySelector('.js-payer-cpfCnpj');
    
    this.init = function() {
        payerReference.addEventListener("atlas-select-change", _this.editInfo);
    };

    this.editInfo = function() {
        const selectedPayer = payerReference.value;
        const selectedOption = payerReference.querySelector('atlas-option[value="' + selectedPayer + '"]');
        const payerEmail =  selectedOption.getAttribute('email');
        const payerCpfCnpj=  selectedOption.getAttribute('cpfCnpj');

        payerEmailReference.value = payerEmail;
        payerCpfCnpjReference.value = payerCpfCnpj;
    };
}

var paymentShowController;

document.addEventListener("DOMContentLoaded", () => {
    paymentShowController = new PaymentShowController();
    paymentShowController.init();
})