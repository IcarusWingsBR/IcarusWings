function NotificationController() {
    var notificationBellReference = document.querySelector(".js-notification-button");
    var contentReference = document.querySelector(".js-div-content");

    var init = function() {
        bindButton();
    };

    var bindButton = function() {
        notificationBellReference.addEventListener("atlas-dropdown-opened", getNotificationList);
    };

    var getNotificationList = function() {
        Atlas.request.get('/notification/list')
        .then(response => {
            contentReference.innerHTML = response.template;
            notificationBellReference.loading = false;
        });
    };

    init();
}

var notificationController;

document.addEventListener("DOMContentLoaded", () => {
    notificationController = new NotificationController();
})