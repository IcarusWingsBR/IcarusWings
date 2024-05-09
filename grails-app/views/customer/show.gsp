<html>
<head>
  <meta name="layout" content="">
  <title>Mostrar payer</title>
</head>
<body>
<div class="background-box">
  <div>
    <label for="name">Nome</label>
    <input type="text" id="name" value="${customer.name}">
  </div>
  <div>
    <label for="email">Email</label>
    <input type="text" id="email" value="${customer.email}">
  </div>
  <div>
    <label for="cpfCnpj">Data Criação</label>
    <input type="text" id="cpfCnpj" value="${customer.cpfCnpj}">
  </div>
  <div>
    <label for="dateCreated">Data Criação</label>
    <input type="text" id="dateCreated" value="${customer.dateCreated}">
  </div>
</div>
</body>
</html>