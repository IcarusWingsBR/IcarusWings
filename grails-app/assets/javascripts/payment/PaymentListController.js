function PaymentListController() {
    this.reference = document.querySelector(".js-list-panel");
    var _this = this;
    var deleteButtonsReference = _this.reference.querySelectorAll('.js-delete-button');
    var modalDeleteReference = _this.reference.querySelector('.js-delete-modal');
    var closeDeleteModalButtonReference = _this.reference.querySelector('.js-close-delete-modal-button');
    var restoreButtonsReference = _this.reference.querySelectorAll('.js-restore-button');
    var modalRestoreReference = _this.reference.querySelector('.js-restore-modal');
    var closeRestoreModalButtonReference = _this.reference.querySelector('.js-close-restore-modal-button');
    var deleteInputIdReference = _this.reference.querySelector('.js-delete-input-id');
    var restoreInputIdReference = _this.reference.querySelector('.js-restore-input-id');

    this.init = function() {
        _this.bindDeleteButtons();
        _this.bindRestoreButtons();
        closeDeleteModalButtonReference.addEventListener("atlas-button-click", _this.closeDeleteModal);
        closeRestoreModalButtonReference.addEventListener("atlas-button-click", _this.closeRestoreModal);
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

    this.openDeleteModal = function(event) {
        deleteInputIdReference.value = event.currentTarget.id;
        modalDeleteReference.setAttribute("open", "");
    };

    this.closeDeleteModal = function() {
        modalDeleteReference.removeAttribute("open");
    };

    this.openRestoreModal = function(event) {
        restoreInputIdReference.value = event.currentTarget.id;
        modalRestoreReference.setAttribute("open", "");
    };

    this.closeRestoreModal = function() {
        modalRestoreReference.removeAttribute("open");
    };
}

var paymentListController;

document.addEventListener("DOMContentLoaded", () => {
    paymentListController = new PaymentListController();
    paymentListController.init();
})