function UserListController() {
    this.reference = document.querySelector(".js-list-panel");
    var _this = this;
    var deleteButtonsReference = _this.reference.querySelectorAll('.js-delete-button');
    var deleteModalReference = _this.reference.querySelector('.js-delete-modal');
    var closeModalButtonReference = _this.reference.querySelector('.js-close-delete-modal-button');
    var restoreButtonsReference = _this.reference.querySelectorAll('.js-restore-button');
    var restoreModalReference = _this.reference.querySelector('.js-restore-modal');
    var restoreInputIdReference = _this.reference.querySelector('.js-restore-input-id');
    var deleteInputIdReference = _this.reference.querySelector('.js-delete-input-id');

    this.init = function() {
        _this.bindDeleteButtons();
        _this.bindRestoreButtons();
        closeModalButtonReference.addEventListener("click", _this.closeDeleteModal);
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

    this.openDeleteModal = function(event) {
        deleteInputIdReference.value = event.currentTarget.id;
        deleteModalReference.setAttribute("open", "");
    };

    this.closeDeleteModal = function() {
        deleteModalReference.removeAttribute("open");
    };

    this.openRestoreModal = function(event) {
        restoreInputIdReference.value = event.currentTarget.id;
        restoreModalReference.setAttribute("open", "");
    };
}

var userListController;

document.addEventListener("DOMContentLoaded", () => {
    userListController = new UserListController();
    userListController.init();
})