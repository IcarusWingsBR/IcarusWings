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
        }
    }

    @Override
    List<String> listAllowedFilters() {
        return ["id"]
    }

    @Override
    BuildableCriteria getBuildableCriteria() {
        return User.createCriteria()
    }

}