package icaruswings.repositories

import icaruswings.User
import org.grails.datastore.mapping.query.api.BuildableCriteria

class UserRepository implements Repository<User, UserRepository> {

    @Override
    void buildCriteria() {
        addCriteria {
            if (search.containsKey("id")) {
                eq("id", Long.valueOf(search.id.toString()))
            }

            if (search.containsKey("customerId")) {
                    eq("customer.id", Long.valueOf(search.customerId.toString()))
            }
        }
    }

    @Override
    List<String> listAllowedFilters() {
        return [
                "id",
                "customerId"
        ]
    }

    @Override
    BuildableCriteria getBuildableCriteria() {
        return User.createCriteria()
    }
}