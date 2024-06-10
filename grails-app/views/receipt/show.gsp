<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Comprovante de pagamento</title>
    <asset:stylesheet href="receipt/receipt.css"/>
    <link
      rel="stylesheet"
      href="https://atlas.asaas.com/v15.18.0/atlas.min.css"
      crossorigin="anonymous">
    <script
      defer
      src="https://atlas.asaas.com/v15.18.0/atlas.min.js"
      crossorigin="anonymous"
    ></script>
</head>
<body>
  <atlas-panel header="Comprovante de pagamento" class="container">
    <p class="date-created">Gerado em ${formatTagLib.formatedDate(date: receipt.dateCreated)}</p>
    <div class="div-border">
      <p><strong>Forma de pagamento:</strong> ${message(code: 'PaymentType.' + receipt.payment.paymentType + '.label')} </p> 
      <p><strong>Valor pago:</strong> ${receipt.payment.value}</p> 
      <p><strong>Data do vencimento:</strong> ${formatTagLib.formatedDate(date: receipt.payment.dueDate)}</p>
      <p><strong>Data do pagamento:</strong> ${formatTagLib.formatedDate(date: receipt.dateCreated)}</p>
    </div>
    <div class="div-border">
      <p><strong>Dados do pagador</strong></p> 
      <p><strong>Nome:</strong> ${receipt.payment.payer.name}</p> 
      <p><strong>Email:</strong> ${receipt.payment.payer.email}</p>
    </div>
    <div class="div-border">
      <p><strong>Dados do recebedor</strong></p> 
      <p><strong>Nome:</strong> ${receipt.payment.payer.customer.name}</p> 
      <p><strong>Email:</strong> ${receipt.payment.payer.customer.email}</p>
      <p><strong>Instituição:</strong> Icarus Wings Instituição Financeira</p>
    </div>
  </atlas-panel>
</body>
</html>