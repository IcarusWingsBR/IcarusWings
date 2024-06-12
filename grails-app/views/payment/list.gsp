<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="layout" content="main">
    <title>Cobranças</title>
    <asset:javascript src="payment/PaymentListController.js"/>
</head>
<body page-title="Cobranças">
    <atlas-filter >
        <atlas-form slot="simple-filter" method="POST" action="${createLink(customer: "payment", action: "list")}">
            <atlas-filter-group header="Listar Cobranças" name="listarCobrancas">
                <atlas-radio value="ativas" checked class="js-filter-options">Cobranças Ativas</atlas-radio>
                <atlas-radio value="excluidas" class="js-filter-options">Cobranças Excluídas</atlas-radio>
            </atlas-filter-group>
            <atlas-button class="js-filter-button" submit description="Filtrar"></atlas-button>
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
                    </g:each>
                </atlas-table-body>
            </atlas-table>
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
        <g:else>
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
        </g:else>
        <g:if test="${flash.message}">
            <atlas-modal header="${flash.type == "success" ? "Cobrança editada" : "Erro"}" open="">${flash.message}</atlas-modal>
        </g:if>
    </atlas-panel>
</body>
</html>