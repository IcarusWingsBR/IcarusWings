function PaymentListController() {
    this.reference = document.querySelector(".js-list-panel");
    var _this = this;
    var deleteButtonsReference = _this.reference.querySelectorAll('.js-delete-button');
    var modalReference = _this.reference.querySelector('.js-modal');
    var closeModalButtonReference = _this.reference.querySelector('.js-close-modal-button');
    var deletePaymentButtonReference = _this.reference.querySelector('.js-delete-payment-button');
    var paymentId;

    this.init = function() {
        _this.bindDeleteButtons();
        closeModalButtonReference.addEventListener("atlas-icon-button-click", _this.closeModal);
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

    this.deletePayment =  function() {
        fetch(`/payment/delete/${paymentId}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) return response.text();
            
            throw new Error('Failed to delete instance');
        })
        .then(result => {
            alert('Deleted successfully');
        })
        .catch(error => {
            alert('Erro ao deletar cobrança');
        });

        _this.closeModal();
    };
}

var paymentListController;

document.addEventListener("DOMContentLoaded", () => {
    paymentListController = new PaymentListController();
    paymentListController.init();
})