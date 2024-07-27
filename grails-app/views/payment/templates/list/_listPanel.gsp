<atlas-panel class="js-list-panel">
    <g:if test="${ paymentList }">
        <atlas-toolbar>
            <atlas-button
                    icon="plus"
                    description="Adicionar cobrança"
                    href="${createLink(controller: "payment", action: "index")}"
                    slot="actions"
            ></atlas-button>
        </atlas-toolbar>
        <g:render template="templates/list/listTable/listTable" model="${[paymentList: paymentList]}" />
        <atlas-modal header="Excluir Cobrança" class="js-delete-modal">
            <atlas-form method="POST" action="${createLink(customer: "payment", action: "delete")}">
                Você realmente quer excluir essa cobrança?
                <atlas-button-group>
                    <atlas-button description="Excluir" theme="danger" submit></atlas-button>
                    <atlas-button description="Cancelar" theme="secondary" class="js-close-delete-modal-button"></atlas-button>
                </atlas-button-group>
                <atlas-input
                    class="js-delete-input-id"
                    value=""
                    name="id"
                    hidden
                >
            </atlas-form>
        </atlas-modal> 
        <atlas-modal header="Restaurar Cobrança" class="js-restore-modal">
            <atlas-form method="POST" action="${createLink(customer: "payment", action: "restore")}">
                Você realmente quer restaurar essa cobrança?
                <atlas-button-group>
                    <atlas-button description="Restaurar" theme="primary" submit></atlas-button>
                    <atlas-button description="Cancelar" theme="secondary" class="js-close-restore-modal-button"></atlas-button>
                </atlas-button-group>
                <atlas-input
                    class="js-restore-input-id"
                    value=""
                    name="id"
                    hidden
                >
            </atlas-form>
        </atlas-modal>
    </g:if>
    <g:render template="templates/list/listTable/emptyTable" model="${[paymentList: paymentList]}" />
    <g:if test="${flash.message}">
        <atlas-modal header="${flash.type == "success" ? "Cobrança editada" : "Erro"}" open="">${flash.message}</atlas-modal>
    </g:if>
</atlas-panel>