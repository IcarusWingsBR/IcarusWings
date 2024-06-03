<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="layout" content="main">
    <title>Todas as Cobrança</title>
</head>
<body page-title="Todas as Cobrança">
    <atlas-panel>
        <g:if test="${ paymentList }">
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
                                ${formatTagLib.formatedDateCreated(date: payment.dateCreated)}
                            </atlas-table-col>
                            <atlas-table-col>
                                ${formatTagLib.formatedDateCreated(date: payment.dueDate)}
                            </atlas-table-col>
                            <atlas-button-group slot="actions" group-all>
                                <atlas-icon-button
                                        icon="pencil"
                                        theme="primary"
                                        description="Editar cobrança"
                                        href="${createLink(controller: "payment", action: "show", id: payment.id)}"
                                >
                                </atlas-icon-button>
                            </atlas-button-group>
                        </atlas-table-row>
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
        <g:if test="${flash.message}">
            <atlas-modal header="Erro" open="">${flash.message}</atlas-modal>
        </g:if>
    </atlas-panel>
</body>
</html>