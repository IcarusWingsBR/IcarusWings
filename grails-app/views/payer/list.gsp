<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pagadores</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Nome</th>
        <th>Email</th>
        <th>cpfCnpj</th>
        <th>postalCode</th>
        <th>address</th>
        <th>address</th>
        <th>province</th>
        <th>city</th>
        <th>state</th>
        <th>addressNumber</th>
        <th>addressComplement</th>
        <th>phone</th>
        <th>personType</th>
        <th>customerId</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${payerList}" var="payer">
        <tr>
            <td>${payer.name}</td>
            <td>${payer.email}</td>
            <td>${payer.cpfCnpj}</td>
            <td>${payer.postalCode}</td>
            <td>${payer.address}</td>
            <td>${payer.province}</td>
            <td>${payer.city}</td>
            <td>${payer.state}</td>
            <td>${payer.addressNumber}</td>
            <td>${payer.addressComplement}</td>
            <td>${payer.phone}</td>
            <td>${payer.personType}</td>
            <td>${payer.customer.id}</td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>
</html>