@GrailsCompileStatic
class Payment {
    Payer payer
    PaymentType type
    Double value
    String status
    Date dueDate

    static constraints = {
        payer nullable: false
        type nullable: false
        value nullable: false
        status nullable: false, blank: false
        dueDate nullable: false
    }
}

enum PaymentType {
    CREDIT_CARD,
    DEBIT_CARD,
    BANK_TRANSFER,
    PIX,
    CASH
}