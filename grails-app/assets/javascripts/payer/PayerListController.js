function PayerListController() {
    this.reference = document.querySelector(".js-list-panel");
    var filterOptionsReference = document.querySelectorAll(".js-filter-options");
    var _this = this;
    var deleteHandler;
    var restoreHandler;
    var deletedListReference = _this.reference.querySelector(".js-deleted-list");
    var deleteButtonsReference = _this.reference.querySelectorAll('.js-delete-button');
    var deleteModalReference = _this.reference.querySelector('.js-delete-modal');
    var closeModalButtonReference = _this.reference.querySelector('.js-close-delete-modal-button');
    var deletePayerButtonReference = _this.reference.querySelector('.js-delete-payer-button');
    var listReference = _this.reference.querySelector(".js-list");
    var restoreButtonsReference = _this.reference.querySelectorAll('.js-restore-button');
    var restoreModalReference = _this.reference.querySelector('.js-restore-modal');
    var restorePayerButtonReference = _this.reference.querySelector('.js-restore-payer-button');

    var payerId;

    this.init = function() {
        deleteHandler = new DeleteHandler();
        restoreHandler = new RestoreHandler();
        _this.bindDeleteButtons();
        _this.bindRestoreButtons();
        _this.bindFilterOptions();
        closeModalButtonReference.addEventListener("click", _this.closeDeleteModal);
        deletePayerButtonReference.addEventListener("atlas-button-click", _this.deletePayer);
        restorePayerButtonReference.addEventListener("atlas-button-click", _this.restorePayer);
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
        });
    };

    this.bindFilterOptions = function() {
        filterOptionsReference.forEach(option => {
            option.addEventListener('atlas-radio-change', this.changeDisplay);
        });
    }

    this.openDeleteModal = function(event) {
        payerId = event.currentTarget.id;
        deleteModalReference.setAttribute("open", "");
    };

    this.closeDeleteModal = function() {
        deleteModalReference.removeAttribute("open");
    };

    this.openRestoreModal = function(event) {
        payerId = event.currentTarget.id;
        restoreModalReference.setAttribute("open", "");
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

    this.deletePayer = async function() {
        await deleteHandler.fetchDelete("payer", payerId)
            .then(() => {
                alert('Deletado com sucesso');
            })
            .catch(() => {
                alert('Erro ao deletar pagador');
            });

        window.location.reload();
    };

    this.restorePayer = async function() {
        await restoreHandler.fetchRestore("payer", payerId)
            .then(() => {
                alert('Restaurado com sucesso');
            })
            .catch(() => {
                alert('Erro ao restaurar cobrança');
            });

        window.location.reload();
    };
}

var payerListController;

document.addEventListener("DOMContentLoaded", () => {
    payerListController = new PayerListController();
    payerListController.init();
})