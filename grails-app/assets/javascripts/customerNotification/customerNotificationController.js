function CustomerNotificationController() {
    var notificationBellReference = document.querySelector(".js-notification-button");
    var contentReference = document.querySelector(".js-div-content");

    var init = function() {
        bindButton();
    };

    var bindButton = function() {
        notificationBellReference.addEventListener("atlas-dropdown-opened", getNotificationList);
    };

    var getNotificationList = function() {
        Atlas.request.get('/customerNotification/list')
        .then(response => {
            contentReference.innerHTML = response.template;
            notificationBellReference.loading = false;
        });
    };

    init();
}

var customerNotificationController;

document.addEventListener("DOMContentLoaded", () => {
    customerNotificationController = new CustomerNotificationController();
})