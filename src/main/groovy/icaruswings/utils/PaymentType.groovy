package icaruswings.utils

enum PaymentType {
    TICKET,
    CARD,
    PIX

    public static PaymentType convert(String paymentType) {
        try {
            if (paymentType instanceof String) paymentType = paymentType.toUpperCase()
            return paymentType as PaymentType
        } catch(Exception e) {
            return null
        }
    }
}