<html>
<head>
    <title>Pagadores</title>
    <meta name="layout" content="main">
    <asset:javascript src="payer/PayerListController.js"/>
</head>
<body page-title="Pagadores">
    <atlas-filter >
        <atlas-form slot="simple-filter" method="POST" action="${createLink(customer: "payer", action: "list")}">
            <atlas-filter-group header="Listar Pagadores" name="payerList">
                <atlas-radio value="active" checked class="js-filter-options">Pagadores Ativos</atlas-radio>
                <atlas-radio value="deleted" class="js-filter-options">Pagadores Excluídos</atlas-radio>
            </atlas-filter-group>
            <atlas-button class="js-filter-button" submit description="Filtrar"></atlas-button>
        </atlas-form>
    </atlas-filter>
    <g:if test="${ payerList }">
        <atlas-panel class="js-list-panel">
            <atlas-table has-actions class="js-list">
                <atlas-table-header slot="header">
                    <atlas-table-col>
                        Nome
                    </atlas-table-col>
                    <atlas-table-col>
                        E-mail
                    </atlas-table-col>
                    <atlas-table-col>
                        Telefone
                    </atlas-table-col>
                    <atlas-table-col>
                        Cpf/Cnpj
                    </atlas-table-col>
                    <atlas-table-col>
                        Data de Criação
                    </atlas-table-col>
                </atlas-table-header>
                <atlas-table-body slot="body">
                    <g:each var="payer" in="${ payerList }">
                        <atlas-table-row href="${createLink(controller: "payer", action: "show", id: payer.id)}">
                            <atlas-table-col>
                                ${payer.name}
                            </atlas-table-col>
                            <atlas-table-col>
                                ${payer.email}
                            </atlas-table-col>
                            <atlas-table-col>
                                ${payer.phone}
                            </atlas-table-col>
                            <atlas-table-col>
                                ${payer.cpfCnpj}
                            </atlas-table-col>
                            <atlas-table-col>
                                ${formatTagLib.formatedDate(date: payer.dateCreated)}
                            </atlas-table-col>
                            <g:if test="${ !payer.deleted }">
                                <atlas-button-group slot="actions">
                                    <atlas-icon-button
                                            icon="trash"
                                            theme="primary"
                                            description="Excluir pagagor"
                                            class="js-delete-button"
                                            id="${payer.id}"
                                    >
                                    </atlas-icon-button>
                                </atlas-button-group>
                            </g:if>
                            <g:else>
                                <atlas-button-group slot="actions">
                                    <atlas-icon-button
                                            icon="refresh-dollar"
                                            theme="primary"
                                            description="Restaurar pagador"
                                            class="js-restore-button"
                                            id="${payer.id}"
                                    >
                                    </atlas-icon-button>
                                </atlas-button-group>
                            </g:else>
                        </atlas-table-row>
                    </g:each>
                </atlas-table-body>
            </atlas-table>
            <atlas-modal header="Excluir Pagador" class="js-delete-modal">
                <atlas-form method="POST" action="${createLink(customer: "payer", action: "delete")}">
                    Você realmente quer excluir esse pagador?
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
            <atlas-modal header="Restaurar Pagador" class="js-restore-modal">
                <atlas-form method="POST" action="${createLink(customer: "payer", action: "restore")}">
                        Você realmente quer restaurar esse pagador?
                        <atlas-button-group>
                            <atlas-button class="margin-modal-button" submit description="Restaurar" theme="primary"></atlas-button>
                        </atlas-button-group>
                        <atlas-input
                            class="js-restore-input-id"
                            value=""
                            name="id"
                            hidden
                        >
                </atlas-form>
            </atlas-modal>
        </atlas-panel>
    </g:if>
    <g:else>
        <atlas-empty-state
                illustration="schedule-user-avatar"
                header="Sem pagadores cadastrados"
        >
            Aqui você pode cadastrar os pagadores que deseja utilizar em suas transações.
            <atlas-button
                    icon="plus"
                    description="Adicionar pagador"
                    href="${createLink(controller: "payer", action: "index")}"
                    slot="button"
            ></atlas-button>
        </atlas-empty-state>
    </g:else>
    <g:if test="${flash.message}">
        <atlas-modal header="${flash.type == "success" ? "Cobrança editada" : "Erro"}" open="">${flash.message}</atlas-modal>
    </g:if>
</body>
</html>