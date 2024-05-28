<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Clientes</title>
</head>
<body>
<table>
  <thead>
  <tr>
    <th>Nome</th>
    <th>Email</th>
    <th>Cpf/Cnpj</th>
    <th>Cep</th>
    <th>Endereço</th>
    <th>Bairro</th>
    <th>Cidade</th>
    <th>Estado</th>
    <th>Número do endereço</th>
    <th>Complemento do endereço</th>
    <th>Telefone</th>
    <th>Tipo de pessoa</th>
  </tr>
  </thead>
  <tbody>
  <g:each in="${customerList}" var="customer">
    <tr>
      <td>${customer.name}</td>
      <td>${customer.email}</td>
      <td>${customer.cpfCnpj}</td>
      <td>${customer.postalCode}</td>
      <td>${customer.address}</td>
      <td>${customer.province}</td>
      <td>${customer.city}</td>
      <td>${customer.state}</td>
      <td>${customer.addressNumber}</td>
      <td>${customer.addressComplement}</td>
      <td>${customer.phone}</td>
      <td>${customer.personType}</td>
    </tr>
  </g:each>
  </tbody>
</table>
</body>
</html>