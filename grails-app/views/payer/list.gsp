<html>
<head>
    <title>Cadastrar Pagador</title>
    <meta name="layout" content="main">
    <asset:javascript src="payer/PayerListController.js"/>
    <asset:javascript src="delete/DeleteHandler.js"/>
</head>
<body page-title="Cadastrar pagador">
<atlas-panel class="js-list-panel">
    <g:if test="${ payerList }">
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
                    </atlas-table-row>
                    <atlas-modal header="Excluir Pagador" class="js-modal">
                            Você realmente quer excluir esse pagador?
                    <atlas-button description="Excluir" theme="danger" slot="actions" class="js-delete-payer-button"></atlas-button>
                    <atlas-button description="Cancelar" theme="secondary" slot="actions" class="js-close-modal-button"></atlas-button>
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