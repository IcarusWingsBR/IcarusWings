<html>
    <head>
        <meta name="layout" content="">
        <title>Cadastro de Cliente</title>
        <asset:stylesheet href="utilsCss/index.css"/>
        <asset:javascript src="validator/PostalCodeValidator.js"/>
        <asset:javascript src="BasePersonController.js"/>
    </head>
    <body>
        <div class="background-box js-save-customer-form">
            <form action="${createLink(controller:"customer", action: "save")}">
                <img src="${resource(dir: 'images', file: 'logo.png')}" alt="Logo" class="img-logo">

                <h1 class="color-title">Formulário de Cadastro</h1>

                <label for="name">Nome:</label><br>
                <input name="name" id="name" placeholder="Digite seu Nome" value="${params.name}" class="form-field" required/><br>

                <label for="email">Email:</label><br>
                <input name="email" id="email" placeholder="Digite seu Email" value="${params.email}" class="form-field" required/><br>

                <label for="cpfCnpj">CPF/CNPJ:</label><br>
                <input name="cpfCnpj" id="cpfCnpj" placeholder="Digite seu CPF ou CNPJ" value="${params.cpfCnpj}" class="form-field" required/><br>

                <label for="phone">Número de telefone:</label><br>
                <input name="phone" id="phone" placeholder="Digite seu Número de Telefone" value="${params.phone}" class="form-field" required/><br>

                <label for="postalCode">CEP:</label><br>
                <input type="text" name="postalCode" id="postalCode" placeholder="Digite seu CEP" class="form-field js-postalCode"
                    value="" size="10" maxlength="9" value="${params.postalCode}" autocomplete="off" required/>

                <label for="address">Rua:</label><br>
                <input type="text" name="address" id="address" placeholder="Digite sua Rua" class="form-field js-address" value="${params.address}" autocomplete="off"/>

                <label for="province">Bairro:</label><br>
                <input type="text" name="province" id="province" placeholder="Digite seu Bairro" class="form-field js-province" value="${params.province}" autocomplete="off"/>

                <label for="city">Cidade:</label><br>
                <input type="text" name="city" id="city" placeholder="Digite sua Cidade" class="form-field js-city" value="${params.city}" autocomplete="off"/>

                <label for="state">Estado:</label><br>
                <input type="text" name="state" id="state" placeholder="Digite seu Estado" class="form-field js-state" value="${params.state}" autocomplete="off"/>

                <label for="addressNumber">Número:</label><br>
                <input type="addressNumber" name="addressNumber" id="addressNumber" placeholder="Digite o número de sua residência" class="form-field" value="${params.addressNumber}" autocomplete="off" required/>

                <label for="addressComplement">Complemento:</label><br>
                <input type="text" name="addressComplement" id="addressComplement" placeholder="Digite o Complemento" class="form-field" value="${params.addressComplement}" autocomplete="off" required/>

                <button type="submit" class="input-button">Enviar</button>
            </form>
        </div>
    </body>
</html>