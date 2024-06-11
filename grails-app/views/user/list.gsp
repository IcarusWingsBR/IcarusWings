<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="layout" content="main">
  <title>Todos os Usuários</title>
</head>
<body page-title="Todos os Usuários">
<atlas-panel class="js-list-panel">
  <g:if test="${ userList }">
    <atlas-toolbar>
    </atlas-toolbar>
    <atlas-table has-actions>
      <atlas-table-header slot="header">
        <atlas-table-col>
          Nome
        </atlas-table-col>
      </atlas-table-header>
      <atlas-table-body slot="body">
        <g:each var="user" in="${ userList }">
          <atlas-table-row>
            <atlas-table-col>
              ${user}
            </atlas-table-col>
          </atlas-table-row>
        </g:each>
      </atlas-table-body>
    </atlas-table>
  </g:if>
</atlas-panel>
</body>
</html>