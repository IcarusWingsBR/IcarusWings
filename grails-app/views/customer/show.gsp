<html>
<head>
    <meta name="layout" content="main">
    <title>Minha Conta</title>
    <asset:javascript src="validator/PostalCodeValidator.js"/>
    <asset:javascript src="/basePerson/BasePersonController.js"/>
</head>
<body page-title="Detalhes do usuário">
<atlas-form-panel action="${createLink(controller: "customer", action: "update")}" header="Detalhes do cliente - ${customer.name}" class="js-save-person-form" method="POST">
    <atlas-input
            value="${customer.id}"
            name="id"
            hidden
    >
    </atlas-input>
    <atlas-button slot="actions" description="Editar" data-panel-start-editing></atlas-button>
    <atlas-grid>
        <atlas-row>
            <atlas-col>
                <atlas-input
                        label="Nome"
                        name="name"
                        required="true"
                        value="${customer.name}"
                >
                </atlas-input>
            </atlas-col>
        </atlas-row>
        <atlas-row>
            <atlas-col lg="6">
                <atlas-masked-input
                        label="Cpf/Cnpj"
                        name="cpfCnpj"
                        mask-alias="cpf-cnpj"
                        value="${customer.cpfCnpj}"
                        required="true"
                >
                </atlas-masked-input>
            </atlas-col>
            <atlas-col lg="6">
                <atlas-input
                        label="Tipo de Pessoa"
                        name="personType"
                        value="${message(code: 'PersonType.' + customer.personType + '.label')}"
                        required="true"
                        readonly
                >
                </atlas-input>
            </atlas-col>
        </atlas-row>
        <atlas-row>
            <atlas-col lg="6">
                <atlas-masked-input
                        label="Email"
                        name="email"
                        mask-alias="email"
                        value="${customer.email}"
                        required="true"
                >
                </atlas-masked-input>
            </atlas-col>
            <atlas-col lg="6">
                <atlas-masked-input
                        label="Telefone/Celular"
                        name="phone"
                        mask-alias="phone"
                        value="${customer.phone}"
                        required="true"
                >
                </atlas-masked-input>
            </atlas-col>
        </atlas-row>
    </atlas-row>
        <atlas-row>
            <atlas-col lg="6">
                <atlas-input
                        label="CEP"
                        name="postalCode"
                        value="${customer.postalCode}"
                        required="true"
                        class="js-postalCode"
                >
                </atlas-input>
            </atlas-col>
            <atlas-col lg="6">
                <atlas-input
                        label="Rua"
                        name="address"
                        value="${customer.address}"
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
                        value="${customer.province}"
                        class="js-province"
                >
                </atlas-input>
            </atlas-col>
            <atlas-col lg="6">
                <atlas-input
                        label="Número do endereço"
                        name="addressNumber"
                        required="true"
                        value="${customer.addressNumber}"
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
                        value="${customer.city}"
                        class="js-city"
                >
                </atlas-input>
            </atlas-col>
            <atlas-col lg="6">
                <atlas-input
                        label="Estado"
                        name="state"
                        required="true"
                        value="${customer.state}"
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
                        value="${customer.addressComplement}"
                >
                </atlas-input>
            </atlas-col>
            <atlas-col lg="6">
                <atlas-input
                        label="Data de criação"
                        name="dateCreated"
                        value="${formatTagLib.formatedDate(date: customer.dateCreated)}"
                        required="true"
                        readonly
                >
                </atlas-input>
            </atlas-col>
        </atlas-row>
    </atlas-grid>
</atlas-form-panel>
</body>
</html>