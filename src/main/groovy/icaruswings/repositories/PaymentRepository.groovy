package icaruswings.repositories
import icaruswings.payment.Payment
import icaruswings.payment.PaymentStatus
import org.grails.datastore.mapping.query.api.BuildableCriteria

class PaymentRepository implements Repository<Payment, PaymentRepository> {

    @Override
    void buildCriteria() {
        addCriteria {
            if (PaymentRepository.joinWithPayer(search)) {
                createAlias("payer", "payer")
            }

            if (search.containsKey("id")) {
                eq("id", Long.valueOf(search.id.toString()))
            }

            if (search.containsKey("payerCustomerId")) {
                eq("payer.customer.id", Long.valueOf(search.payerCustomerId.toString()))
            }

            if (search.containsKey("paymentStatus")) {
                eq("paymentStatus", PaymentStatus.valueOf(search.paymentStatus.toString()))
            }

            if (search.containsKey("dueDate[lt]")) {
                lt("dueDate", search."dueDate[lt]")
            }

            if (search.containsKey("payer")) {
                eq("payer.id", Long.valueOf(search.payer.toString()))
            }

            if (search.containsKey("paymentStatus[in]")) {
                inList("paymentStatus", search."paymentStatus[in]".collect { PaymentStatus.valueOf(it.toString()) })
            }
        }
    }

    @Override
    List<String> listAllowedFilters() {
        return [
            "id",
            "payerCustomerId",
            "paymentStatus",
            "dueDate[lt]",
            "payer",
            "paymentStatus[in]"
        ]
    }

    @Override
    BuildableCriteria getBuildableCriteria() {
        return Payment.createCriteria()
    }

    private static joinWithPayer(Map search) {
        return search.containsKey("payerCustomerId")
    }
}