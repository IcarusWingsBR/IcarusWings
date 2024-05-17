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
    <label for="personType">Tipo de pessoa</label>
    <input type="text" id="personType" value="${payer.personType}">
  </div>
  <div>
    <label for="phoneNumber">Telefone</label>
    <input type="text" id="phoneNumber" value="${payer.phoneNumber}">
  </div>
  <div>
    <label for="CEP">CEP</label>
    <input type="text" id="CEP" value="${payer.cep}">
  </div>
  <div>
    <label for="street">Rua</label>
    <input type="text" id="street" value="${payer.street}">
  </div>
  <div>
    <label for="neighborhood">Bairro</label>
    <input type="text" id="neighborhood" value="${payer.neighborhood}">
  </div>
  <div>
    <label for="city">Cidade</label>
    <input type="text" id="city" value="${payer.city}">
  </div>
  <div>
    <label for="state">Estado</label>
    <input type="text" id="state" value="${payer.state}">
  </div>
  <div>
    <label for="number">Número da residência:</label>
    <input type="text" id="number" value="${payer.number}">
  </div>
  <div>
    <label for="complement">Complemento</label>
    <input type="text" id="complement" value="${payer.complement}">
  </div>
  <div>
    <label for="dateCreated">Data Criação</label>
    <input type="text" id="dateCreated" value="${payer.dateCreated}">
  </div>
  <div>
    <label for="customerId">Id do cliente</label>
    <input type="text" id="customerId" value="${payer.customer.id}">
    <label for="customerName">Nome do cliente</label>
    <input type="text" id="customerName" value="${payer.customer.name}">
  </div>
</div>
</body>
</html>