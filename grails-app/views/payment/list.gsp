<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="layout" content="main">
    <title>Cobranças</title>
    <asset:javascript src="payment/PaymentListController.js"/>
    <asset:javascript src="delete/DeleteHandler.js"/>
    <asset:javascript src="restore/RestoreHandler.js"/>
</head>
<body page-title="Cobranças">

    <atlas-filter >
        <atlas-form slot="simple-filter">
            <atlas-filter-group header="Listar Cobranças" name="listarCobrancas">
                <atlas-radio value="ativas" checked class="js-filter-options">Cobranças Ativas</atlas-radio>
                <atlas-radio value="excluidas" class="js-filter-options">Cobranças Excluídas</atlas-radio>
            </atlas-filter-group>
            <atlas-button class="js-filter-button" description="Filtrar"></atlas-button>
        </atlas-form>
    </atlas-filter>

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
                        <atlas-modal header="Excluir Cobrança" class="js-delete-modal">
                            Você realmente quer excluir essa cobrança?
                            <atlas-button description="Excluir" theme="danger" slot="actions" class="js-delete-payment-button"></atlas-button>
                            <atlas-button description="Cancelar" theme="secondary" slot="actions" class="js-close-delete-modal-button"></atlas-button>
                        </atlas-modal> 
                        <atlas-modal header="Restaurar Cobrança" class="js-restore-modal">
                            Você realmente quer restaurar essa cobrança?
                            <atlas-button description="Restaurar" theme="primary" slot="actions" class="js-restore-payment-button"></atlas-button>
                            <atlas-button description="Cancelar" theme="secondary" slot="actions" class="js-close-restore-modal-button"></atlas-button>
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