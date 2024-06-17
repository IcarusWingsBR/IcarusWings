<html>
<head>
  <title>Usuários</title>
  <meta name="layout" content="main">
  <asset:javascript src="user/UserListController.js"/>
</head>
<body page-title="Lista de usuários">
<atlas-filter >
  <atlas-form slot="simple-filter" method="POST" action="${createLink(customer: "user", action: "list")}">
    <atlas-filter-group header="Listar Usuários" name="userList">
      <atlas-radio value="active" checked class="js-filter-options">Usuários Ativos</atlas-radio>
      <atlas-radio value="deleted" class="js-filter-options">Usuários Excluídos</atlas-radio>
    </atlas-filter-group>
    <atlas-button class="js-filter-button" submit description="Filtrar"></atlas-button>
  </atlas-form>
</atlas-filter>
<g:if test="${ userList }">
  <atlas-panel class="js-list-panel">
    <atlas-table has-actions class="js-list">
      <atlas-table-header slot="header">
        <atlas-table-col>
          Email de usuário
        </atlas-table-col>
        <atlas-table-col>
          Data de criação
        </atlas-table-col>
      </atlas-table-header>
      <atlas-table-body slot="body">
        <g:each var="user" in="${ userList }">
          <atlas-table-row href="${createLink(controller: "user", action: "show", id: user.id)}">
            <atlas-table-col>
              ${user.username}
            </atlas-table-col>
            <atlas-table-col>
              ${formatTagLib.formatedDate(date: user.dateCreated)}
            </atlas-table-col>
            <g:if test="${ !user.deleted }">
              <atlas-button-group slot="actions">
                <atlas-icon-button
                        icon="trash"
                        theme="primary"
                        description="Excluir usuário"
                        class="js-delete-button"
                        id="${user.id}"
                >
                </atlas-icon-button>
              </atlas-button-group>
            </g:if>
            <g:else>
              <atlas-button-group slot="actions">
                <atlas-icon-button
                        icon="refresh-dollar"
                        theme="primary"
                        description="Restaurar usuário"
                        class="js-restore-button"
                        id="${user.id}"
                >
                </atlas-icon-button>
              </atlas-button-group>
            </g:else>
            <atlas-button-group slot="actions" group-all>
              href="${createLink(controller: "user", action: "show", id: user.id)}"
            </atlas-button-group>
          </atlas-table-row>
        </g:each>
      </atlas-table-body>
    </atlas-table>
    <atlas-modal header="Excluir Usuário" class="js-delete-modal">
      <atlas-form method="POST" action="${createLink(customer: "user", action: "delete")}">
        Você realmente quer excluir esse usuário?
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
    <atlas-modal header="Restaurar Usuário" class="js-restore-modal">
      <atlas-form method="POST" action="${createLink(customer: "user", action: "restore")}">
        Você realmente quer restaurar esse usuário?
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
          header="Sem usuários cadastrados"
  >
    Aqui você pode cadastrar os usuários que deseja utilizar em suas transações.
    <atlas-button
            icon="plus"
            description="Adicionar usuário"
            href="${createLink(controller: "user", action: "index")}"
            slot="button"
    ></atlas-button>
  </atlas-empty-state>
</g:else>
<g:if test="${flash.message}">
  <atlas-modal header="${flash.type == "success" ? "Usuário editado" : "Erro"}" open="">${flash.message}</atlas-modal>
</g:if>
</body>
</html>