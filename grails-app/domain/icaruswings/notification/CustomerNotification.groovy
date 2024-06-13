package icaruswings.notification

import icaruswings.Customer
import icaruswings.utils.BaseEntity

class CustomerNotification extends BaseEntity {

    String title

    String message

    String url

    CustomerNotificationType type

    Customer customer

    static constraints = {
        title blank: false
        message blank: false
        url blank: false
        type blank: false
        customer blank: false
    }
}