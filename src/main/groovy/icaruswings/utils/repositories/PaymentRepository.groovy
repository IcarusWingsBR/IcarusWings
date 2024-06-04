package icaruswings.utils.repositories
import icaruswings.payment.Payment
import org.grails.datastore.mapping.query.api.BuildableCriteria

class PaymentRepository implements Repository<Payment, PaymentRepository> {

    @Override
    void buildCriteria() {
        addCriteria {
            if (search.containsKey("id")) {
                eq("id", Long.valueOf(search.id.toString()))
            }
        }
    }

    @Override
    List<String> listAllowedFilters() {
        return [
                "id"
        ]
    }

    @Override
    BuildableCriteria getBuildableCriteria() {
        return Payment.createCriteria()
    }
}