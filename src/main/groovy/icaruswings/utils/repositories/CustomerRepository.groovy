package icaruswings.utils.repositories

import icaruswings.Customer
import org.grails.datastore.mapping.query.api.BuildableCriteria

class CustomerRepository implements Repository<Customer, CustomerRepository> {

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
        return Customer.createCriteria()
    }
}
