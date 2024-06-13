package icaruswings

import grails.plugin.springsecurity.annotation.Secured
import grails.converters.JSON
import icaruswings.notification.CustomerNotification

@Secured(['IS_AUTHENTICATED_FULLY'])
class CustomerNotificationController extends BaseController {

    CustomerNotificationService customerNotificationService

    def list() {
        List<CustomerNotification> customerNotifications = customerNotificationService.list((getAuthenticatedUser() as User).customerId)
        String template = g.render(template: "/customerNotification/list", model: [customerNotifications: customerNotifications])

        render([
            template: template
        ] as JSON )
    }
}