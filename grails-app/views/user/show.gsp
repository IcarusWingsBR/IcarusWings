<html>
<head>
  <meta name="layout" content="main">
  <title>Meu Usuário</title>
  <asset:javascript src="user/UserShowController.js"/>
</head>
<body page-title="Detalhes do usuário">
<atlas-form-panel action="${createLink(controller: "user", action: "update")}" header="Detalhes do usuário - ${user.username}" class="js-save-person-form" method="POST" >
  <atlas-input
          value="${user.id}"
          name="id"
          hidden
  >
  </atlas-input>
  <g:if test="${!user.deleted}">
    <atlas-button-group slot="actions">
      <atlas-button description="Editar" data-panel-start-editing></atlas-button>
      <atlas-button
              icon="trash"
              theme="primary"
              description="Excluir usuário"
              class="js-open-modal-button"
      >
      </atlas-button>
    </atlas-button-group>
  </g:if>
  <atlas-grid>
    <atlas-row>
      <atlas-col lg="6">
        <atlas-input
                label="Nome de usuário"
                name="email"
                required="true"
                value="${user.username}"
        >
        </atlas-input>
      </atlas-col>
      <atlas-col lg="6">
        <atlas-password-input
                label="Nova senha"
                name="password"
                required="true"
        >
        </atlas-password-input>
      </atlas-col>
    </atlas-row>
  </atlas-grid>
  <atlas-modal header="Excluir usuário" class="js-modal">
    Você realmente quer excluir esse usuário?
    <atlas-button description="Excluir" theme="danger" slot="actions" href="${createLink(controller: "user", action: "delete", id: "${user.id}")}"></atlas-button>
    <atlas-button description="Cancelar" theme="secondary" slot="actions" class="js-close-modal-button"></atlas-button>
  </atlas-modal>
</atlas-form-panel>
<g:if test="${flash.message}">
  <atlas-modal header="${flash.type == "success" ? "Usuário editado" : "Erro"}" open="">${flash.message}</atlas-modal>
</g:if>
</body>
</html>