<html>
<head>
  <title>Clientes</title>
  <meta name="layout" content="main">
</head>
<body page-title="Lista de clientes">
<atlas-panel>
  <g:if test="${ customerList }">
    <atlas-toolbar>
      <atlas-button
              icon="plus"
              description="Adicionar cliente"
              href="${createLink(controller: "customer", action: "index")}"
              slot="actions"
      ></atlas-button>
    </atlas-toolbar>
    <atlas-table has-actions>
      <atlas-table-header slot="header">
        <atlas-table-col>
          Nome
        </atlas-table-col>
        <atlas-table-col>
          E-mail
        </atlas-table-col>
        <atlas-table-col>
          Telefone
        </atlas-table-col>
        <atlas-table-col>
          Cpf/Cnpj
        </atlas-table-col>
        <atlas-table-col>
          Data de Criação
        </atlas-table-col>
      </atlas-table-header>
      <atlas-table-body slot="body">
        <g:each var="customer" in="${ customerList }">
          <atlas-table-row href="${createLink(controller: "customer", action: "show", id: customer.id)}">
            <atlas-table-col>
              ${customer.name}
            </atlas-table-col>
            <atlas-table-col>
              ${customer.email}
            </atlas-table-col>
            <atlas-table-col>
              ${customer.phone}
            </atlas-table-col>
            <atlas-table-col>
              ${customer.cpfCnpj}
            </atlas-table-col>
            <atlas-table-col>
              ${formatTagLib.formatedDate(date: customer.dateCreated)}
            </atlas-table-col>

            <atlas-button-group slot="actions" group-all>
              <atlas-icon-button
                      icon="pencil"
                      theme="primary"
                      description="Editar cliente"
                      href="${createLink(controller: "customer", action: "show", id: customer.id)}"
              >
              </atlas-icon-button>
            </atlas-button-group>
          </atlas-table-row>
        </g:each>
      </atlas-table-body>
    </atlas-table>
  </g:if>
  <g:else>
    <atlas-empty-state
            illustration="schedule-user-avatar"
            header="Sem clientes cadastrados"
    >
      Aqui você pode cadastrar os clientes que deseja utilizar em suas transações.
      <atlas-button
              icon="plus"
              description="Adicionar cliente"
              href="${createLink(controller: "customer", action: "index")}"
              slot="button"
      ></atlas-button>
    </atlas-empty-state>
  </g:else>
</atlas-panel>
</body>
</html>