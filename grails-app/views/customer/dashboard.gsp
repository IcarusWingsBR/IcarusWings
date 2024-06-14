<html>
<head>
    <meta name="layout" content="main">
    <title>Minha Conta</title>
    <asset:javascript src="validator/PostalCodeValidator.js"/>
    <asset:javascript src="/basePerson/BasePersonController.js"/>
    <asset:stylesheet href="/customer/dashboard.css"/>
</head>
<body page-title="Dashboard">
    <atlas-panel header="Você pode gostar de conferir:">
        <a href="${createLink(controller: "customer", action: "show")}">
            <atlas-card header="Sua conta">
                Clique aqui para ver informações da sua conta
            </atlas-card>
        </a>
        <a href="${createLink(controller: "payer", action: "list")}">
             <atlas-card header="Seus Pagadores">
                Clique aqui para ver seus pagadores
            </atlas-card>
        </a>
        <a href="${createLink(controller: "payment", action: "list")}">
                <atlas-card header="Suas Cobranças">
                    Clique aqui para ver suas cobranças
                </atlas-card>
        </a>
    </atlas-panel>
</body>
</html>