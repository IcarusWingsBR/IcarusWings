package icaruswings.notification

import icaruswings.Customer
import icaruswings.utils.BaseEntity

class Notification extends BaseEntity {

    String title

    String message

    String url

    NotificationType type

    Customer customer

    static constraints = {
        title nullable: false, blank: false
        message nullable: false, blank: false
        url nullable: false, blank: false
        type nullable: false, blank: false
        customer nullable: false, blank: false
    }
}