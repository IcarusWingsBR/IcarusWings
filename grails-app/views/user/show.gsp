<html>
<head>
  <meta name="layout" content="main">
  <title>Meu Usuário</title>
</head>
<body page-title="Detalhes do usuário">
<atlas-form-panel action="${createLink(controller: "user", action: "update")}" header="Detalhes do usuário - ${user.username}" method="POST">
  <atlas-input
          value="${user.id}"
          name="id"
          hidden
  >
  </atlas-input>
  <atlas-button slot="actions" description="Editar" data-panel-start-editing></atlas-button>
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
</atlas-form-panel>
</body>
</html>