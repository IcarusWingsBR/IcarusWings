class ExpireOverduePaymentsJob {

    def paymentService

    static triggers = {
        cron name: "expireOverdueJobTrigger", cronExpression: "0 0 0 * * ?"
    }

    def execute() {
        try {
            paymentService.processOverduePayments()
        } catch (Exception exception) {
            log.error("Erro ao vencer um pagamento", exception)
        }
    }
}