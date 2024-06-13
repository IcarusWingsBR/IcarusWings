<g:if test="${notifications}">
    <g:each in="${notifications}" var="notification">
        <atlas-notification-card
            icon="hand-holding-money"
            overlay-icon="money-notes"
            overlay-theme="success"
            header="${notification.title}"
            description="${notification.message}"
            link-path="${notification.url}"
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