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
    <label for="postalCode">CEP</label>
    <input type="text" id="postalCode" value="${payer.postalCode}">
  </div>
  <div>
    <label for="address">Rua</label>
    <input type="text" id="address" value="${payer.address}">
  </div>
  <div>
    <label for="province">Bairro</label>
    <input type="text" id="province" value="${payer.province}">
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
    <label for="addressNumber">Número da residência:</label>
    <input type="text" id="addressNumber" value="${payer.addressNumber}">
  </div>
  <div>
    <label for="addressComplement">Complemento</label>
    <input type="text" id="addressComplement" value="${payer.addressComplement}">
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