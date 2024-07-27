<g:if test="${ !paymentList }">
    <atlas-empty-state
        illustration="schedule-user-avatar"
        header="Sem cobranças cadastradas"
    >
        Aqui você pode cadastrar as cobranças que deseja utilizar em suas transações.
        <atlas-button
            icon="plus"
            description="Criar cobrança"
            href="${createLink(controller: "payment", action: "index")}"
            slot="button"
        ></atlas-button>
    </atlas-empty-state>
</g:if>