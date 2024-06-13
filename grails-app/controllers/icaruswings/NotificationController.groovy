package icaruswings

import grails.plugin.springsecurity.annotation.Secured
import grails.converters.JSON
import icaruswings.notification.Notification

@Secured(['IS_AUTHENTICATED_FULLY'])
class NotificationController extends BaseController {

    NotificationService notificationService

    def list() {
        List<Notification> notifications = notificationService.list((getAuthenticatedUser() as User).customerId)
        String template = g.render(template: "/notification/list", model: [notifications: notifications])

        render([
            template: template
        ] as JSON )
    }
}