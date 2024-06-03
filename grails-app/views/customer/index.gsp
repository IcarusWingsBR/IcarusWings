<html>
    <head>
        <meta name="layout" content="main">
        <title>Cadastrar Cliente</title>
        <asset:javascript src="validator/PostalCodeValidator.js"/>
        <asset:javascript src="/customer/BasePersonController.js"/>
    </head>
<body page-title="Cadastrar cliente">
<atlas-panel class="js-save-person-form">
    <atlas-form action="${createLink(customer: "customer", action: "save")}">
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
                <atlas-col>
                    <atlas-input
                            label="Complemento"
                            name="addressComplement"
                            required="true"
                    >
                    </atlas-input>
                </atlas-col>
            </atlas-row>
        </atlas-grid>
        <atlas-button submit description="Salvar"></atlas-button>
    </atlas-form>
</atlas-panel>
</body>
</html>