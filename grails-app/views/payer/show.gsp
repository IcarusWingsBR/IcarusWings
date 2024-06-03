<html>
<head>
  <title>Detalhes do Pagador</title>
  <meta name="layout" content="main">
  <meta charset="UTF-8">
  <asset:javascript src="validator/PostalCodeValidator.js"/>
  <asset:javascript src="/customer/BasePersonController.js"/>
</head>
<body page-title="Detalhes do Pagador">
<atlas-form-panel action="${createLink(controller: "payer", action: "update")}" header="Detalhes do pagador - ${payer.name}" class="js-save-person-form">
  <atlas-input
          value="${payer.id}"
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
                value="${payer.name}"
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
                value="${payer.cpfCnpj}"
                required="true"
        >
        </atlas-masked-input>
      </atlas-col>
      <atlas-col lg="6">
        <atlas-input
                label="Tipo de Pessoa"
                name="personType"
                value="${message(code: 'PersonType.' + payer.personType + '.label')}"
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
                value="${payer.email}"
                required="true"
        >
        </atlas-masked-input>
      </atlas-col>
      <atlas-col lg="6">
        <atlas-masked-input
                label="Telefone/Celular"
                name="phone"
                mask-alias="phone"
                value="${payer.phone}"
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
                value="${payer.postalCode}"
                required="true"
                class="js-postalCode"
        >
        </atlas-input>
      </atlas-col>
      <atlas-col lg="6">
        <atlas-input
                label="Rua"
                name="address"
                value="${payer.address}"
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
                value="${payer.province}"
                class="js-province"
        >
        </atlas-input>
      </atlas-col>
      <atlas-col lg="6">
        <atlas-input
                label="Número do endereço"
                name="addressNumber"
                required="true"
                value="${payer.addressNumber}"
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
                value="${payer.city}"
                class="js-city"
        >
        </atlas-input>
      </atlas-col>
      <atlas-col lg="6">
        <atlas-input
                label="Estado"
                name="state"
                required="true"
                value="${payer.state}"
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
                value="${payer.addressComplement}"
        >
        </atlas-input>
      </atlas-col>
      <atlas-col lg="6">
        <atlas-input
                label="Data de criação"
                name="dateCreated"
                value="${formatTagLib.formatedDate(date: payer.dateCreated)}"
                required="true"
                readonly
        >
        </atlas-input>
      </atlas-col>
    </atlas-row>
    <atlas-row>
      <atlas-col lg="6">
        <atlas-input
                label="Id do Cliente vinculado"
                name="customerId"
                required="true"
                readonly
                value="${payer.customer.id}"
        >
        </atlas-input>
      </atlas-col>
      <atlas-col lg="6">
        <atlas-input
                label="Nome do Cliente vinculado"
                name="customerName"
                required="true"
                readonly
                value="${payer.customer.name}"
        >
        </atlas-input>
      </atlas-col>
    </atlas-row>
  </atlas-grid>
</atlas-form-panel>
</body>
</html>