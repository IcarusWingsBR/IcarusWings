<html>
<head>
  <title>Cadastrar Pagador</title>
  <meta name="layout" content="main">
</head>
<body page-title="Cadastrar pagador">
<atlas-panel>
  <atlas-form action="${createLink(customer: "payer", action: "save")}">
    <atlas-grid>
      <atlas-row>
        <atlas-col>
          <atlas-select
                  label="Cliente"
                  name="customerId"
                  required="true"
          >
            <g:each var="customer" in="${ customerList }">
              <atlas-option label="${customer.name}" value="${customer.id}"></atlas-option>
            </g:each>
          </atlas-select>
        </atlas-col>
      </atlas-row>
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
          >
          </atlas-input>
        </atlas-col>
        <atlas-col lg="6">
          <atlas-input
                  label="Rua"
                  name="address"
                  required="true"
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
          >
          </atlas-input>
        </atlas-col>
        <atlas-col lg="6">
          <atlas-input
                  label="Estado"
                  name="state"
                  required="true"
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