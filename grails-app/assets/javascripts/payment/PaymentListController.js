function PaymentListController() {
    this.reference = document.querySelector(".js-list-panel");
    var filterOptionsReference = document.querySelectorAll(".js-filter-options");
    var filterButtonReference = document.querySelector(".js-filter-button");
    var _this = this;
    var deleteHandler;
    var restoreHandler;
    var deleteButtonsReference = _this.reference.querySelectorAll('.js-delete-button');
    var modalDeleteReference = _this.reference.querySelector('.js-delete-modal');
    var closeDeleteModalButtonReference = _this.reference.querySelector('.js-close-delete-modal-button');
    var deletePaymentButtonReference = _this.reference.querySelector('.js-delete-payment-button');
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
        _this.bindChangeFilterOptionChecked();
        closeDeleteModalButtonReference.addEventListener("atlas-button-click", _this.closeDeleteModal);
        closeRestoreModalButtonReference.addEventListener("atlas-button-click", _this.closeRestoreModal);
        deletePaymentButtonReference.addEventListener("atlas-button-click", _this.deletePayment);
        restorePaymentButtonReference.addEventListener("atlas-button-click", _this.restorePayment);
        filterButtonReference.addEventListener("click", _this.changeList);
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

    this.bindChangeFilterOptionChecked =  function() {
        const url = window.location.href;
        const activePaymentsOption = filterOptionsReference[0];
        const deletedPaymentsOption = filterOptionsReference[1];

        if (url == "http://localhost:8080/payment/list/") {
            activePaymentsOption.checked = true;
            console.log("aaaaaaa")
        } else if (url == "http://localhost:8080/payment/list/excluidas") {
            deletedPaymentsOption.checked = true;
            console.log("bbbbbbbbbb")
        }
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

    this.changeList = function() {
        const ativasOption = filterOptionsReference[0];
        const excluidasOption = filterOptionsReference[1];
        var url = "/payment/list";

        if (ativasOption.checked) { 
            window.location.href =  url;
        } else if (excluidasOption.checked) {
            window.location.href =  url + "/excluidas";
        }
    };

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