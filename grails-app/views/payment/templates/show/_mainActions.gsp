<g:if test="${ !payment.deleted }">
    <atlas-button-group slot="actions" group-after="3">
        <g:if test="${ payment.paymentStatus.toString() != 'PAYED' }">
            <atlas-button description="Editar" data-panel-start-editing></atlas-button>
            <atlas-button
                icon="check"
                theme="primary"
                description="Confirmar Pagamento"
                class="js-open-confirm-received-modal-button"
            ></atlas-button>
        </g:if>
        <atlas-button
            icon="trash"
            theme="primary"
            description="Excluir cobrança"
            class="js-open-delete-modal-button"
        ></atlas-button>
    </atlas-button-group>
</g:if>