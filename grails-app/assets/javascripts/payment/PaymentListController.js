function PaymentListController() {
    this.reference = document.querySelector(".js-list-panel");
    var filterOptionsReference = document.querySelectorAll(".js-filter-group");
    var _this = this;
    var deleteHandler;
    var restoreHandler;
    var listReference = _this.reference.querySelector(".js-list");
    var deleteButtonsReference = _this.reference.querySelectorAll('.js-delete-button');
    var modalDeleteReference = _this.reference.querySelector('.js-delete-modal');
    var closeDeleteModalButtonReference = _this.reference.querySelector('.js-close-delete-modal-button');
    var deletePaymentButtonReference = _this.reference.querySelector('.js-delete-payment-button');
    var deletedListReference = _this.reference.querySelector(".js-deleted-list");
    var restoreButtonsReference = _this.reference.querySelectorAll('.js-restore-button');
    var modalRestoreReference = _this.reference.querySelector('.js-restore-modal');
    var closeRestoreModalButtonReference = _this.reference.querySelector('.js-close-restore-modal-button');
    var restorePaymentButtonReference = _this.reference.querySelector('.js-restore-payment-button');
    var paymentId;

    this.init = function() {
        deleteHandler = new DeleteHandler();
        restoreHandler = new RestoreHandler();
        _this.bindDeleteButtons();
        _this.bindRestoreButtons();
        _this.bindFilterOptions();
        closeDeleteModalButtonReference.addEventListener("atlas-button-click", _this.closeDeleteModal);
        closeRestoreModalButtonReference.addEventListener("atlas-button-click", _this.closeRestoreModal);
        deletePaymentButtonReference.addEventListener("atlas-button-click", _this.deletePayment);
        restorePaymentButtonReference.addEventListener("atlas-button-click", _this.restorePayment);
        deletedListReference.remove();
    };

    this.bindDeleteButtons = function() {
        deleteButtonsReference.forEach(deleteButton => {
            deleteButton.addEventListener('click', this.openDeleteModal);
        })
    };

    this.bindRestoreButtons = function() {
        restoreButtonsReference.forEach(restoreButton => {
            restoreButton.addEventListener('click', this.openRestoreModal);
        })
    };

    this.bindFilterOptions = function() {
        filterOptionsReference.forEach(option => {
            option.addEventListener('atlas-radio-change', this.changeDisplay);
        });
    }

    this.openDeleteModal = function(event) {
        paymentId = event.currentTarget.id;
        modalDeleteReference.setAttribute("open", "");
    };

    this.closeDeleteModal = function() {
        modalDeleteReference.removeAttribute("open");
    };

    this.openRestoreModal = function(event) {
        paymentId = event.currentTarget.id;
        modalRestoreReference.setAttribute("open", "");
    };

    this.closeRestoreModal = function() {
        modalRestoreReference.removeAttribute("open");
    };

    this.changeDisplay =  function(event) {
        const isChecked = event.target.checked;
        const value = event.target.value;

        if(value == 'ativas' && isChecked) {
            deletedListReference.remove();
            _this.reference.appendChild(listReference);
        } else if (value == 'excluidas' && isChecked) {
            listReference.remove();
            _this.reference.appendChild(deletedListReference);
        }
    }

    this.deletePayment = async function() {
        await deleteHandler.fetchDelete("payment", paymentId)
            .then(() => {
                alert('Deletado com sucesso');
            })
            .catch(() => {
                alert('Erro ao deletar cobrança');
            });

        window.location.reload();
    };

    this.restorePayment = async function() {
        await restoreHandler.fetchRestore("payment", paymentId)
            .then(() => {
                alert('Restaurado com sucesso');
            })
            .catch(() => {
                alert('Erro ao restaurar cobrança');
            });

        window.location.reload();
    };
}

var paymentListController;

document.addEventListener("DOMContentLoaded", () => {
    paymentListController = new PaymentListController();
    paymentListController.init();
})