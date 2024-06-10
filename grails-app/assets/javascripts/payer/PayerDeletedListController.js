function PayerDeletedListController() {
    this.reference = document.querySelector(".js-list-panel");
    var _this = this;
    var restoreHandler;
    var restoreButtonsReference = _this.reference.querySelectorAll('.js-restore-button');
    var modalReference = _this.reference.querySelector('.js-modal');
    var restorePayerButtonReference = _this.reference.querySelector('.js-restore-payer-button');
    var payerId;

    this.init = function() {
        restoreHandler = new RestoreHandler();
        _this.bindDeleteButtons();
        restorePayerButtonReference.addEventListener("atlas-button-click", _this.restorePayer);
    };

    this.bindDeleteButtons = function() {
        restoreButtonsReference.forEach(restoreButton => {
            restoreButton.addEventListener('click', this.openModal);
        });
    };

    this.openModal = function(event) {
        payerId = event.currentTarget.id;
        modalReference.setAttribute("open", "");
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

var payerDeletedListController;

document.addEventListener("DOMContentLoaded", () => {
    payerDeletedListController = new PayerDeletedListController();
    payerDeletedListController.init();
})