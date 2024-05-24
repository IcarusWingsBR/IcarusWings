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
                <label for="CEP">CEP</label>
                <input type="text" id="CEP" value="${customer.cep}">
            </div>
            <div>
                <label for="street">Rua</label>
                <input type="text" id="street" value="${customer.street}">
            </div>
            <div>
                <label for="city">Cidade</label>
                <input type="text" id="city" value="${customer.city}">
            </div>
            <div>
                <label for="number">Número de residência</label>
                <input type="text" id="number" value="${customer.number}">
            </div>
            <div>
                <label for="complement">Complemento</label>
                <input type="text" id="complement" value="${customer.complement}">
            </div>
            <div>
                <label for="dateCreated">Data Criação</label>
                <input type="text" id="dateCreated" value="${customer.dateCreated}">
            </div>
        </div>
    </body>
</html>