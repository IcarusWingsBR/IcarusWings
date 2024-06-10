function PayerDeletedListController() {
    this.reference = document.querySelector(".js-list-panel");
    var _this = this;
    var restoreButtonsReference = _this.reference.querySelectorAll('.js-restore-button');
    var modalReference = _this.reference.querySelector('.js-modal');

    this.init = function() {
        _this.bindDeleteButtons();
    };

    this.bindDeleteButtons = function() {
        restoreButtonsReference.forEach(restoreButton => {
            restoreButton.addEventListener('click', this.openModal);
        });
    };

    this.openModal = function(event) {
        modalReference.setAttribute("open", "");
    };
}

var payerDeletedListController;

document.addEventListener("DOMContentLoaded", () => {
    payerDeletedListController = new PayerDeletedListController();
    payerDeletedListController.init();
})