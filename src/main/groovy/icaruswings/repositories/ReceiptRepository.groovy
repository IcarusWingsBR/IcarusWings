package icaruswings.repositories

import icaruswings.Receipt
import org.grails.datastore.mapping.query.api.BuildableCriteria

class ReceiptRepository implements Repository<Receipt, ReceiptRepository> {

    @Override
    void buildCriteria() {
        addCriteria {
            if (search.containsKey("token")) {
                eq("token", search.token.toString())
            }
        }
    }

    @Override
    List<String> listAllowedFilters() {
        return ["token"]
    }

    @Override
    BuildableCriteria getBuildableCriteria() {
        return Receipt.createCriteria()
    }
}