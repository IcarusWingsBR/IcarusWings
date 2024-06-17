<html>
<head>
    <meta name="layout" content="main">
    <title>Cadastrar Novo Usuário</title>
</head>
<body page-title="Cadastrar usuário">
<atlas-panel>
    <atlas-form action="${createLink(controller: "user", action: "addUser")}" method="POST">
        <atlas-grid>
            <atlas-row>
                <atlas-col lg="6">
                    <atlas-input
                            label="Nome de usuário"
                            name="email"
                            required="true"
                    >
                    </atlas-input>
                </atlas-col>
                <atlas-col lg="6">
                    <atlas-password-input
                            label="Senha"
                            name="password"
                            required="true"
                    >
                    </atlas-password-input>
                </atlas-col>
            </atlas-row>
        </atlas-grid>
        <atlas-button submit description="Salvar"></atlas-button>
    </atlas-form>
</atlas-panel>
</body>
</html>