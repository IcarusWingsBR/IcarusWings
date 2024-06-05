package icaruswings.payment

enum PaymentStatus {
    PENDING,
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