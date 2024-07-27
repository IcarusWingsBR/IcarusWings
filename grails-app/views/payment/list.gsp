<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="layout" content="main">
    <title>Cobranças</title>
    <asset:javascript src="payment/PaymentListController.js"/>
</head>
<body page-title="Cobranças">
    <g:render template="templates/list/filter" />
    <g:render template="templates/list/listPanel" model="${[paymentList: paymentList]}" />
</body>
</html>