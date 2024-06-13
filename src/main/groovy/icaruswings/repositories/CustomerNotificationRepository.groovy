package icaruswings.repositories

import icaruswings.notification.CustomerNotification
import org.grails.datastore.mapping.query.api.BuildableCriteria

class CustomerNotificationRepository implements Repository<CustomerNotification, CustomerNotificationRepository> {

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
        return CustomerNotification.createCriteria()
    }
}