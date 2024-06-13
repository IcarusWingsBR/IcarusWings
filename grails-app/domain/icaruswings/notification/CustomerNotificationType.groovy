package icaruswings.notification

enum CustomerNotificationType {
    PAYMENT_CREATED,
    PAYMENT_PAYED,
    PAYMENT_DELETED,
    PAYMENT_OVERDUE,
    PAYMENT_UPDATED,
    PAYMENT_RESTORED

    public static CustomerNotificationType convert(String customerNotificationType) {
        try {
            if (customerNotificationType instanceof String) customerNotificationType = customerNotificationType.toUpperCase()
            return customerNotificationType as CustomerNotificationType
        } catch(Exception e) {
            return null
        }
    }
}