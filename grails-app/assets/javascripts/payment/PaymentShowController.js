function PaymentShowController() {
    this.reference = document.querySelector(".js-edit-payment-form");
    var _this = this;
    var payerReference = _this.reference.querySelector(".js-payer");
    var payerEmailReference = _this.reference.querySelector('.js-payer-email');
    var payerCpfCnpjReference = _this.reference.querySelector('.js-payer-cpfCnpj');
    var openModalButtonReference = _this.reference.querySelector('.js-open-modal-button');
    var modalReference = _this.reference.querySelector('.js-modal');
    var closeModalButtonReference = _this.reference.querySelector('.js-close-modal-button');

    this.init = function() {
        payerReference.addEventListener("atlas-select-change", _this.editInfo);
        openModalButtonReference.addEventListener('atlas-button-click', this.openModal);
        closeModalButtonReference.addEventListener("atlas-button-click", _this.closeModal);
    };

    this.editInfo = function() {
        const selectedPayer = payerReference.value;
        const selectedOption = payerReference.querySelector('atlas-option[value="' + selectedPayer + '"]');
        const payerEmail =  selectedOption.getAttribute('email');
        const payerCpfCnpj=  selectedOption.getAttribute('cpfCnpj');

        payerEmailReference.value = payerEmail;
        payerCpfCnpjReference.value = payerCpfCnpj;
    };

    this.openModal = function() {
        modalReference.setAttribute("open", "");
    };

    this.closeModal = function() {
        modalReference.removeAttribute("open");
    };
}

var paymentShowController;

document.addEventListener("DOMContentLoaded", () => {
    paymentShowController = new PaymentShowController();
    paymentShowController.init();
})