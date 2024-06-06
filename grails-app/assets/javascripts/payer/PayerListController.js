function PayerListController() {
    this.reference = document.querySelector(".js-list-panel");
    var _this = this;
    var deleteHandler;
    var deleteButtonsReference = _this.reference.querySelectorAll('.js-delete-button');
    var modalReference = _this.reference.querySelector('.js-modal');
    var closeModalButtonReference = _this.reference.querySelector('.js-close-modal-button');
    var deletePayerButtonReference = _this.reference.querySelector('.js-delete-payer-button');
    var payerId;

    this.init = function() {
        console.log(deleteButtonsReference)
        deleteHandler = new DeleteHandler();
        _this.bindDeleteButtons();
        closeModalButtonReference.addEventListener("atlas-icon-button-click", _this.closeModal);
        deletePayerButtonReference.addEventListener("atlas-button-click", _this.deletePayer);
    };

    this.bindDeleteButtons = function() {
        deleteButtonsReference.forEach(deleteButton => {
            deleteButton.addEventListener('click', this.openModal)
        })
    };

    this.openModal = function(event) {
        payerId = event.currentTarget.id;
        modalReference.setAttribute("open", "");
    };

    this.closeModal = function() {
        modalReference.removeAttribute("open");
    };

    this.deletePayer = async function() {
        await deleteHandler.fetchDelete("payer", payerId)
            .then(() => {
                alert('Deletado com sucesso');
            })
            .catch(() => {
                alert('Erro ao deletar pagador');
            });

        _this.closeModal();

        window.location.reload();
    };
}

var payerListController;

document.addEventListener("DOMContentLoaded", () => {
    payerListController = new PayerListController();
    payerListController.init();
})