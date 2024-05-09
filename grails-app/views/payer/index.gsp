<html>
<head>
  <meta name="layout" content="">
  <title>Cadastro de Pagador</title>
  <style>
  *{
    box-sizing: border-box;
  }
  /* Estilos para a caixa colorida */
  .background-box {
    background-color: #0030b9; /* Cor de fundo da caixa */
    padding: 30px; /* Espaçamento interno da caixa */
    border-radius: 10px; /* Borda arredondada */
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1); /* Sombra suave */
    width: 475px; /* Largura da caixa */
    height: auto; /* Altura automática para ajustar ao conteúdo */
    display: flex;
    justify-content: center;
    align-items: center; /* Centraliza verticalmente */
    margin: 0 auto; /* Centraliza horizontalmente */
  }
  body{
    color: #f1f5fb;
  }
  /* Estilos para os campos do formulário */
  .form-field {
    width: 100%;
    padding: 8px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 16px;
  }

  /* Estilos para os rótulos do formulário */
  label {
    font-weight: bold;
    color: #f1f5fb;
  }

  /* Estilos para o botão de enviar */
  input[type="submit"] {
    width: 100%;
    background-color: #0198ff;
    color: #fff;
    padding: 10px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 18px;
    transition: background-color 0.3s ease;
  }

  input[type="submit"]:hover {
    background-color: #0056b3;
  }

  @media screen and (max-width: 600px) {
    .campo {
      width: 100%;
    }
  }

  /* Estilos para telas maiores */
  @media screen and (min-width: 601px) {
    .campo {
      width: 400px;
    }
  }
  @media screen and (max-width: 600px) {
    .form-field {
      font-size: 14px;
    }
  }

  /* Estilos para telas maiores */
  @media screen and (min-width: 601px) {
    .form-field {
      font-size: 16px;
    }
  }
  /* Estilos para telas menores */
  @media screen and (max-width: 600px) {
    .form-field {
      margin-bottom: 10px;
    }
  }

  </style>

</head>
<body>
<div class="background-box">

  <form action="${createLink(controller:"payer", action: "save") }">
    <img src="${resource(dir: 'images', file: 'logo.png')}" alt="Logo" style="width: 50%; height: auto;">

    <h1 style= "color: #f1f5fb"> Formulário de Cadastro de Pagador</h1>
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
    <input name="name" id="name"  value="${params.name}" class="form-field"/><br>

    <label for="email">Nome:</label><br>
    <input name="email" id="email" value="${params.email}" class="form-field"/><br>

    <label for="cpfCnpj">CPF/CNPJ:</label><br>
    <input name="cpfCnpj" id="cpfCnpj" value="${params.cpfCnpj}" class="form-field"/><br>

    <button type="submit">Enviar</button>
  </form>

</div>

</body>
</html>
