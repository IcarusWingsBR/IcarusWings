<html>
<head>
    <title>Pagador Excluídos</title>
    <meta name="layout" content="main">
    <asset:javascript src="payer/PayerDeletedListController.js"/>
</head>
<body page-title="Pagador Excluídos">
<atlas-panel class="js-list-panel">
    <g:if test="${ deletedList }">
        <atlas-toolbar>
            <atlas-button
                    icon="plus"
                    description="Adicionar pagador"
                    href="${createLink(controller: "payer", action: "index")}"
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
                <g:each var="payer" in="${ deletedList }">
                    <atlas-table-row>
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
                    </atlas-table-row>
                     <atlas-modal header="Restaurar Pagador" class="js-modal">
                        <atlas-form method="POST" action="${createLink(controller: "payer", action: "restore", id: "${payer.id}")}">
                            Você realmente quer restaurar esse pagador?
                            <atlas-button class="margin-modal-button" submit description="Restaurar" theme="primary"></atlas-button>
                        </atlas-form>
                    </atlas-modal>
                </g:each>
            </atlas-table-body>
        </atlas-table>
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
</atlas-panel>
</body>
</html>