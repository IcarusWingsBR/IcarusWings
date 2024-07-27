<atlas-table-body slot="body">
    <g:each var="payment" in="${ paymentList }">
        <atlas-table-row href="${createLink(controller: "payment", action: "show", id: payment.id)}">
            <atlas-table-col>
                ${payment.payer.name}
            </atlas-table-col>
            <atlas-table-col>
                ${payment.payer.email}
            </atlas-table-col>
            <atlas-table-col>
                ${payment.value}
            </atlas-table-col>
            <atlas-table-col>
                ${message(code: 'PaymentType.' + payment.paymentType + '.label')}
            </atlas-table-col>
            <atlas-table-col>
                ${message(code: 'PaymentStatus.' + payment.paymentStatus + '.label')}
            </atlas-table-col>
            <atlas-table-col>
                ${formatTagLib.formatedDate(date: payment.dateCreated)}
            </atlas-table-col>
            <atlas-table-col>
                ${formatTagLib.formatedDate(date: payment.dueDate)}
            </atlas-table-col>
            <g:if test="${ !payment.deleted }">
                <atlas-button-group slot="actions">
                    <atlas-icon-button
                        icon="trash"
                        theme="primary"
                        description="Excluir cobrança"
                        class="js-delete-button"
                        id="${payment.id}"
                    >
                    </atlas-icon-button>
                </atlas-button-group> 
            </g:if>
            <g:else>
                <atlas-button-group slot="actions">
                    <atlas-icon-button
                        icon="refresh-dollar"
                        theme="primary"
                        description="Restaurar cobrança"
                        class="js-restore-button"
                        id="${payment.id}"
                        >
                    </atlas-icon-button>
                </atlas-button-group>
            </g:else>
        </atlas-table-row> 
    </g:each>
</atlas-table-body>