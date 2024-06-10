function PaymentShowController() {
    this.reference = document.querySelector(".js-edit-payment-form");
    var _this = this;
    var payerReference = _this.reference.querySelector(".js-payer");
    var payerEmailReference = _this.reference.querySelector('.js-payer-email');
    var payerCpfCnpjReference = _this.reference.querySelector('.js-payer-cpfCnpj');
    var openDeleteModalButtonReference = _this.reference.querySelector('.js-open-delete-modal-button');
    var deleteModalReference = _this.reference.querySelector('.js-delete-modal');
    var openConfirmReceivedModalButtonReference = _this.reference.querySelector('.js-open-confirm-received-modal-button');
    var confirmReceivedModalReference = _this.reference.querySelector('.js-close-confirm-received-modal');
    

    this.init = function() {
        payerReference.addEventListener("atlas-select-change", _this.editInfo);
        openDeleteModalButtonReference.addEventListener('atlas-button-click', this.openDeleteModal);
        openConfirmReceivedModalButtonReference.addEventListener('atlas-button-click', this.openConfirmReceivedModal);
    };

    this.editInfo = function() {
        const selectedPayer = payerReference.value;
        const selectedOption = payerReference.querySelector('atlas-option[value="' + selectedPayer + '"]');
        const payerEmail =  selectedOption.getAttribute('email');
        const payerCpfCnpj=  selectedOption.getAttribute('cpfCnpj');

        payerEmailReference.value = payerEmail;
        payerCpfCnpjReference.value = payerCpfCnpj;
    };

    this.openDeleteModal = function() {
        deleteModalReference.setAttribute("open", "");
    };

    this.openConfirmReceivedModal = function() {
        confirmReceivedModalReference.setAttribute("open", "");
    };
}

var paymentShowController;

document.addEventListener("DOMContentLoaded", () => {
    paymentShowController = new PaymentShowController();
    paymentShowController.init();
})