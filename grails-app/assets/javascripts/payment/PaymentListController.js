function PaymentListController() {
    this.reference = document.querySelector(".js-list-panel");
    var _this = this;
    var deleteHandler;
    var deleteButtonsReference = _this.reference.querySelectorAll('.js-delete-button');
    var modalReference = _this.reference.querySelector('.js-modal');
    var closeModalButtonReference = _this.reference.querySelector('.js-close-modal-button');
    var deletePaymentButtonReference = _this.reference.querySelector('.js-delete-payment-button');
    var paymentId;

    this.init = function() {
        deleteHandler = new DeleteHandler();
        _this.bindDeleteButtons();
        closeModalButtonReference.addEventListener("atlas-button-click", _this.closeModal);
        deletePaymentButtonReference.addEventListener("atlas-button-click", _this.deletePayment);
    };

    this.bindDeleteButtons = function() {
        deleteButtonsReference.forEach(deleteButton => {
            deleteButton.addEventListener('click', this.openModal)
        })
    };

    this.openModal = function(event) {
        paymentId = event.currentTarget.id;
        modalReference.setAttribute("open", "");
    };

    this.closeModal = function() {
        modalReference.removeAttribute("open");
    };

    this.deletePayment = async function() {
        await deleteHandler.fetchDelete("payment", paymentId)
            .then(() => {
                alert('Deletado com sucesso');
            })
            .catch(() => {
                alert('Erro ao deletar cobrança');
            });
        
        _this.closeModal();

        window.location.reload();
    };
}

var paymentListController;

document.addEventListener("DOMContentLoaded", () => {
    paymentListController = new PaymentListController();
    paymentListController.init();
})