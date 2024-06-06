package icaruswings.repositories
import icaruswings.payment.Payment
import icaruswings.payment.PaymentStatus
import org.grails.datastore.mapping.query.api.BuildableCriteria

class PaymentRepository implements Repository<Payment, PaymentRepository> {

    @Override
    void buildCriteria() {
        addCriteria {
            if (search.containsKey("id")) {
                eq("id", Long.valueOf(search.id.toString()))
            }

            if (search.containsKey("customer")) {
                payer {
                    eq("customer.id", Long.valueOf(search.customer.toString()))
                }
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
                "customer",
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
}