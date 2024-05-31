<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pagamentos</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Pagador</th>
        <th>Tipo do pagamento</th>
        <th>Valor</th>
        <th>Status do pagamento</th>
        <th>Data de vencimento</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${paymentList}" var="payment">
        <tr>
            <td>${payment.payer.id}</td>
            <td>${payment.paymentType}</td>
            <td>${payment.value}</td>
            <td>${payment.paymentStatus}</td>
            <td>${payment.dueDate}</td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>
</html>