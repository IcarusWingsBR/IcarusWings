<g:if test="${customerNotifications}">
    <g:each in="${customerNotifications}" var="customerNotification">
        <atlas-notification-card
            icon="hand-holding-money"
            overlay-icon="money-notes"
            overlay-theme="success"
            header="${customerNotification.title}"
            description="${customerNotification.message}"
            link-path="${customerNotification.url}"
            link-text="Ver Cobrança"
        >
        </atlas-notification-card>
    </g:each>
</g:if>
<g:else>
    <atlas-empty-state
            illustration="airplane-error"
            header="Nenhuma notificação"
    >
        Você não tem nenhuma notificação ainda.
    </atlas-empty-state>
</g:else>