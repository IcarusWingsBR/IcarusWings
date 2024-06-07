<html>
<head>
  <meta name="layout" content="">
  <title>Mostrar receipt</title>
</head>
<body>
<div class="background-box">
  <div>
    <label for="name">data de geração</label>
    <input type="text" id="name" value="${formatTagLib.formatedDate(date: receipt.dateCreated)}">
  </div>
  <div>
    <label for="email">Nome do recebedor</label>
    <input type="text" id="email" value="${receipt.payment.payer.customer.name}">
  </div>
  <div>
    <label for="cpfCnpj">Nome do pagador</label>
    <input type="text" id="cpfCnpj" value="${receipt.payment.payer.name}">
  </div>
</div>
</body>
</html>