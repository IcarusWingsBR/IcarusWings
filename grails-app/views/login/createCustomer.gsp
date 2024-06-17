<html>
<head>
    <title>Cadastrar Cliente</title>
    <asset:javascript src="validator/PostalCodeValidator.js"/>
    <asset:javascript src="/basePerson/BasePersonController.js"/>
    <link
        rel="stylesheet"
        href="https://atlas.asaas.com/v15.18.0/atlas.min.css"
        crossorigin="anonymous">
    <script
        defer
        src="https://atlas.asaas.com/v15.18.0/atlas.min.js"
        crossorigin="anonymous"
    >
    </script>
</head>
<body>
<atlas-screen>
    <atlas-page class="js-atlas-page">
        <atlas-page-header
                slot="header"
                page-name="Cadastrar Cliente"
        >
        </atlas-page-header>
        <atlas-page-content slot="content" class="js-atlas-content">
            <atlas-panel class="js-save-person-form">
                <atlas-form action="${createLink(controller: "customer", action: "save")}" method="POST">
                    <atlas-grid>
                        <atlas-row>
                            <atlas-col lg="6">
                                <atlas-input
                                        label="Nome"
                                        name="name"
                                        required="true"
                                >
                                </atlas-input>
                            </atlas-col>
                            <atlas-col lg="6">
                                <atlas-masked-input
                                        label="Cpf/Cnpj"
                                        name="cpfCnpj"
                                        mask-alias="cpf-cnpj"
                                        required="true"
                                >
                                </atlas-masked-input>
                            </atlas-col>
                        </atlas-row>
                        <atlas-row>
                            <atlas-col lg="6">
                                <atlas-masked-input
                                        label="Email"
                                        name="email"
                                        mask-alias="email"
                                        required="true"
                                >
                                </atlas-masked-input>
                            </atlas-col>
                            <atlas-col lg="6">
                                <atlas-masked-input
                                        label="Telefone/Celular"
                                        name="phone"
                                        mask-alias="phone"
                                        required="true"
                                >
                                </atlas-masked-input>
                            </atlas-col>
                        </atlas-row>
                        <atlas-row>
                            <atlas-col lg="6">
                                <atlas-input
                                        label="CEP"
                                        name="postalCode"
                                        required="true"
                                        class="js-postalCode"
                                >
                                </atlas-input>
                            </atlas-col>
                            <atlas-col lg="6">
                                <atlas-input
                                        label="Rua"
                                        name="address"
                                        required="true"
                                        class="js-address"
                                >
                                </atlas-input>
                            </atlas-col>
                        </atlas-row>
                        <atlas-row>
                            <atlas-col lg="6">
                                <atlas-input
                                        label="Bairro"
                                        name="province"
                                        required="true"
                                        class="js-province"
                                >
                                </atlas-input>
                            </atlas-col>
                            <atlas-col lg="6">
                                <atlas-input
                                        label="Número do endereço"
                                        name="addressNumber"
                                        required="true"
                                >
                                </atlas-input>
                            </atlas-col>
                        </atlas-row>
                        <atlas-row>
                            <atlas-col lg="6">
                                <atlas-input
                                        label="Cidade"
                                        name="city"
                                        required="true"
                                        class="js-city"
                                >
                                </atlas-input>
                            </atlas-col>
                            <atlas-col lg="6">
                                <atlas-input
                                        label="Estado"
                                        name="state"
                                        required="true"
                                        class="js-state"
                                >
                                </atlas-input>
                            </atlas-col>
                        </atlas-row>
                        <atlas-row>
                            <atlas-col lg="6">
                                <atlas-input
                                        label="Complemento"
                                        name="addressComplement"
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
                    <atlas-button-group>
                        <atlas-button submit description="Salvar"></atlas-button>
                        <atlas-button description="Logar" href="${createLink(controller: "login", action: "auth")}"></atlas-button>
                    </atlas-button-group>
                </atlas-form>
            </atlas-panel>
        </atlas-page-content>
    </atlas-page>
</atlas-screen>
</body>
</html>