package icaruswings.repositories

import icaruswings.notification.Notification
import org.grails.datastore.mapping.query.api.BuildableCriteria

class NotificationRepository implements Repository<Notification, NotificationRepository> {

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
        return Notification.createCriteria()
    }
}