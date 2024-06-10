function PaymentDeletedListController() {
    this.reference = document.querySelector(".js-list-panel");
    var _this = this;
    var restoreHandler;
    var restoreButtonsReference = _this.reference.querySelectorAll('.js-restore-button');
    var modalReference = _this.reference.querySelector('.js-modal');
    var closeModalButtonReference = _this.reference.querySelector('.js-close-modal-button');
    var restorePaymentButtonReference = _this.reference.querySelector('.js-restore-payment-button');
    var paymentId;

    this.init = function() {
        restoreHandler = new RestoreHandler();
        _this.bindRestoreButtons();
        closeModalButtonReference.addEventListener("atlas-button-click", _this.closeModal);
        restorePaymentButtonReference.addEventListener("atlas-button-click", _this.restorePayment);
    };

    this.bindRestoreButtons = function() {
        restoreButtonsReference.forEach(restoreButton => {
            restoreButton.addEventListener('click', this.openModal)
        })
    };

    this.openModal = function(event) {
        paymentId = event.currentTarget.id;
        modalReference.setAttribute("open", "");
    };

    this.closeModal = function() {
        modalReference.removeAttribute("open");
    };

    this.restorePayment = async function() {
        await restoreHandler.fetchRestore("payment", paymentId)
            .then(() => {
                alert('Restaurado com sucesso');
            })
            .catch(() => {
                alert('Erro ao restaurar cobrança');
            });
        
        _this.closeModal();

        window.location.reload();
    };
}

var paymentDeletedListController;

document.addEventListener("DOMContentLoaded", () => {
    paymentDeletedListController = new PaymentDeletedListController();
    paymentDeletedListController.init();
})