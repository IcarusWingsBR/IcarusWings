<html>
<head>
  <meta name="layout" content="">
  <title>Mostrar payer</title>
</head>
<body>
<div class="background-box">
  <div>
    <label for="name">Nome</label>
    <input type="text" id="name" value="${payer.name}">
  </div>
  <div>
    <label for="email">Email</label>
    <input type="text" id="email" value="${payer.email}">
  </div>
  <div>
    <label for="cpfCnpj">CPF/CNPJ</label>
    <input type="text" id="cpfCnpj" value="${payer.cpfCnpj}">
  </div>
  <div>
    <label for="dateCreated">Data Criação</label>
    <input type="text" id="dateCreated" value="${payer.dateCreated}">
  </div>
</div>
</body>
</html>