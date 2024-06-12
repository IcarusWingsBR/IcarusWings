package icaruswings.notification

enum NotificationType {
    CREATED,
    PAYED,
    DELETED,
    OVERDUE,
    UPDATED,
    RESTORED

    public static NotificationType convert(String notificationType) {
        try {
            if (notificationType instanceof String) notificationType = notificationType.toUpperCase()
            return notificationType as NotificationType
        } catch(Exception e) {
            return null
        }
    }
}