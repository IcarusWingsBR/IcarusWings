package icaruswings.repositories

import icaruswings.Payer
import org.grails.datastore.mapping.query.api.BuildableCriteria

class PayerRepository implements Repository<Payer, PayerRepository> {

    @Override
    void buildCriteria() {
        addCriteria {
            if (search.containsKey("id")) {
                eq("id", Long.valueOf(search.id.toString()))
            }

            if (search.containsKey("customer")) {
                eq("customer.id", Long.valueOf(search.customer.toString()))
            }
        }
    }

    @Override
    List<String> listAllowedFilters() {
        return [
            "id",
            "customer"
        ]
    }

    @Override
    BuildableCriteria getBuildableCriteria() {
        return Payer.createCriteria()
    }
}