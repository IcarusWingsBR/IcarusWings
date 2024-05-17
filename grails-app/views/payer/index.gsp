<html>
<head>
  <meta name="layout" content="">
  <title>Cadastro de Pagador</title>
</head>
<body>
<div class="background-box">
  <form action="${createLink(controller:"payer", action: "save") }">
    <img src="${resource(dir: 'images', file: 'logo.png')}" alt="Logo" style="width: 50%; height: auto;">

    <h1 style= "color: #f1f5fb"> Formulário de Cadastro de Pagador</h1>

    <label for="name">Cliente:</label>
    <div>
      <g:select
              name="customerId"
              from="${customerList}"
              optionKey="id"
              optionValue="name"
              noSelection="['':'Selecione um cliente']"
      />
    </div>

    <label for="name">Nome:</label><br>
    <input type="text" name="name" id="name" placeholder="Digite seu Nome" value="${params.name}" class="form-field" required/><br>

    <label for="email">Email:</label><br>
    <input type="text" name="email" id="email" placeholder="Digite seu Email" value="${params.email}" class="form-field" required/><br>

    <label for="cpfCnpj">CPF/CNPJ:</label><br>
    <input type="text" name="cpfCnpj" id="cpfCnpj" placeholder="Digite seu CPF ou CNPJ" value="${params.cpfCnpj}" class="form-field" required/><br>

    <label for="phoneNumber">Telefone:</label><br>
    <input type="text" name="phoneNumber" id="phoneNumber" placeholder="Digite o telefone" class="form-field" value="${params.phoneNumber}" autocomplete="off" required/>

    <label for="cep">CEP:</label><br>
    <input type="text" name="cep" id="cep" placeholder="Digite seu CEP" class="form-field"
           value="" size="10" maxlength="9" onblur="pesquisacep(this.value);" value="${params.cep}" autocomplete="off" required/>

    <label for="street">Rua:</label><br>
    <input type="text" name="street" id="street" placeholder="Digite sua Rua" class="form-field" value="${params.street}" autocomplete="off"/>

    <label for="neighborhood">Bairro:</label><br>
    <input type="text" name="neighborhood" id="neighborhood" placeholder="Digite seu Bairro" class="form-field" value="${params.neighborhood}" autocomplete="off"/>

    <label for="city">Cidade:</label><br>
    <input type="text" name="city" id="city" placeholder="Digite sua Cidade" class="form-field" value="${params.city}" autocomplete="off"/>

    <label for="state">Estado:</label><br>
    <input type="text" name="state" id="state" placeholder="Digite seu Estado" class="form-field" value="${params.state}" autocomplete="off"/>

    <label for="number">Número:</label><br>
    <input type="text" name="number" id="number" placeholder="Digite o número de sua residência" class="form-field" value="${params.number}" autocomplete="off" required/>

    <label for="complement">Complemento:</label><br>
    <input type="text" name="complement" id="complement" placeholder="Digite o complemento" class="form-field" value="${params.complement}" autocomplete="off" required/>

    <button type="submit" class="inputButton">Enviar</button>
  </form>
</div>
</body>
</html>