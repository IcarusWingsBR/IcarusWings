package icaruswings.utils.repositories

import icaruswings.Payer
import org.grails.datastore.mapping.query.api.BuildableCriteria

class PayerRepository implements Repository<Payer, PayerRepository> {

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
        return ["id"]
    }

    @Override
    BuildableCriteria getBuildableCriteria() {
        return Payer.createCriteria()
    }
}