<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="layout" content="main">
    <title>Cobranças Excluídas</title>
    <asset:javascript src="payment/PaymentDeletedListController.js"/>
    <asset:javascript src="restore/RestoreHandler.js"/>
</head>
<body page-title="Cobranças Excluídas">
    <atlas-panel class="js-list-panel">
        <g:if test="${ paymentDeletedList }">
            <atlas-toolbar>
                <atlas-button
                        icon="plus"
                        description="Adicionar cobrança"
                        href="${createLink(controller: "payment", action: "index")}"
                        slot="actions"
                ></atlas-button>
            </atlas-toolbar>
            <atlas-table has-actions>
                <atlas-table-header slot="header">
                    <atlas-table-col>
                        Nome
                    </atlas-table-col>
                    <atlas-table-col>
                        E-mail
                    </atlas-table-col>
                    <atlas-table-col>
                        Valor
                    </atlas-table-col>
                    <atlas-table-col>
                        Forma de pagamento
                    </atlas-table-col>
                    <atlas-table-col>
                        Status
                    </atlas-table-col>
                    <atlas-table-col>
                        Data de Criação
                    </atlas-table-col>
                    <atlas-table-col>
                        Data de Vencimento
                    </atlas-table-col>
                </atlas-table-header>
                <atlas-table-body slot="body">
                    <g:each var="payment" in="${ paymentDeletedList }">
                        <atlas-table-row>
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
                        </atlas-table-row>
                        <atlas-modal header="Restaurar Cobrança" class="js-modal">
                            Você realmente quer restaurar essa cobrança?
                            <atlas-button description="Restaurar" theme="primary" slot="actions" class="js-restore-payment-button"></atlas-button>
                            <atlas-button description="Cancelar" theme="secondary" slot="actions" class="js-close-modal-button"></atlas-button>
                        </atlas-modal> 
                    </g:each>
                </atlas-table-body>
            </atlas-table>
        </g:if>
        <g:else>
            <atlas-empty-state
                    illustration="schedule-user-avatar"
                    header="Sem cobranças cadastradas"
            >
                Aqui você pode cadastrar as cobranças que deseja utilizar em suas transações.
                <atlas-button
                        icon="plus"
                        description="Adicionar pagador"
                        href="${createLink(controller: "payment", action: "index")}"
                        slot="button"
                ></atlas-button>
            </atlas-empty-state>
        </g:else>
    </atlas-panel>
</body>
</html>