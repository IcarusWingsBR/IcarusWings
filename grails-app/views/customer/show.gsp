<html>
    <head>
        <meta name="layout" content="">
        <title>Minha Conta</title>
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
                <label for="cpfCnpj">CPF/CNPJ</label>
                <input type="text" id="cpfCnpj" value="${customer.cpfCnpj}">
            </div>
            <div>
                <label for="personType">Tipo de pessoa</label>
                <input type="text" id="personType" value="${customer.personType}">
            </div>
            <div>
                <label for="phone">Número de telefone</label>
                <input type="text" id="phone" value="${customer.phone}">
            </div>
            <div>
                <label for="postalCode">CEP</label>
                <input type="text" id="postalCode" value="${customer.postalCode}">
            </div>
            <div>
                <label for="address">Rua</label>
                <input type="text" id="address" value="${customer.address}">
            </div>
            <div>
                <label for="city">Cidade</label>
                <input type="text" id="city" value="${customer.city}">
            </div>
            <div>
                <label for="state">Estado</label>
                <input type="text" id="state" value="${customer.state}">
            </div>
            <div>
                <label for="number">Número de residência</label>
                <input type="text" id="number" value="${customer.addressNumber}">
            </div>
            <div>
                <label for="addressComplement">Complemento</label>
                <input type="text" id="addressComplement" value="${customer.addressComplement}">
            </div>
            <div>
                <label for="dateCreated">Data Criação</label>
                <input type="text" id="dateCreated" value="${customer.dateCreated}">
            </div>
        </div>
    </body>
</html>