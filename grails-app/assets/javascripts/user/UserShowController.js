function UserShowController() {
    this.reference = document.querySelector(".js-save-person-form");
    var _this = this;
    var openModalButtonReference = _this.reference.querySelector('.js-open-modal-button');
    var modalReference = _this.reference.querySelector('.js-modal');
    var closeModalButtonReference = _this.reference.querySelector('.js-close-modal-button');

    this.init = function() {
        openModalButtonReference.addEventListener('atlas-button-click', this.openModal);
        closeModalButtonReference.addEventListener("atlas-button-click", _this.closeModal);
    };

    this.openModal = function() {
        modalReference.setAttribute("open", "");
    };

    this.closeModal = function() {
        modalReference.removeAttribute("open");
    };
}

var userShowController;

document.addEventListener("DOMContentLoaded", () => {
    userShowController = new UserShowController();
    userShowController.init();
})