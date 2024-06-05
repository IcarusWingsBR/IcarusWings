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

            if (search.containsKey("paymentStatus")) {
                        eq("paymentStatus", PaymentStatus.valueOf(search.paymentStatus.toString()))
            }

            if (search.containsKey("dueDate[lt]")) {
                lt("dueDate", search."dueDate[lt]")
            }

            if (search.containsKey("column")) {
                projections {
                    property "${search.column}"
                }
            }
        }
    }

    @Override
    List<String> listAllowedFilters() {
        return [
                "id",
                "paymentStatus",
                "dueDate[lt]",
                "column"
        ]
    }

    @Override
    BuildableCriteria getBuildableCriteria() {
        return Payment.createCriteria()
    }
}