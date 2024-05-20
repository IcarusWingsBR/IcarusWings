package icaruswings.utils

enum PaymentStatus {
    WAITING_PAYMENT,
    PAYED,
    OVERDUE,
    CANCELED

    public static PaymentStatus convert(String paymentStatus) {
        try {
            if (paymentStatus instanceof String) paymentStatus = paymentStatus.toUpperCase()
            return paymentStatus as PaymentType
        } catch(Exception e) {
            return null
        }
    }
}