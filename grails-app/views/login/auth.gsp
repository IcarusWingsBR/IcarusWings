<g:set var="securityConfig" value="${applicationContext.springSecurityService.securityConfig}"/>

<html>
<head>
  <title>Entrar - Asaas</title>
  <asset:stylesheet href="/login.css"/>
</head>

<body>
<div class="header">
  <asset:image src="/asaas-white.svg"/>
</div>
<div class="container">
  <main>
    <div class="left">
      <img class="logo" src="https://www.asaas.com/assets/login-asaas-preview-90b4ab010e408aec61094f9035d4d150.png" alt=""/>
      <h2 class="description">
        A solução mais completa e segura em emissão de cobranças e serviços financeiros.
      </h2>
    </div>
    <div class="right">
      <h1 class="title">Seja bem-vindo ao Asaas! 🚀</h1>
      <form class="form" action="${postUrl ?: '/login/authenticate'}" method="post" autocomplete="off">
        <div class="form-group">
          <label for="username">Nome</label>
          <input id="username" name="${securityConfig.apf.usernameParameter}" placeholder="Digite seu nome/email"/>
        </div>

        <div class="form-group">
          <label for="password">Senha</label>
          <input id="password" name="${securityConfig.apf.passwordParameter}" type="password"
                 placeholder="Digite sua senha"/>
        </div>

        <button type="submit">Entrar</button>

        <g:if test="${flash.message}">
          <div class="error">${flash.message}</div>
        </g:if>
      </form>
      <a href="${createLink(controller: "customer", action: "index")}" class="create-account">Criar uma nova conta</a>
    </div>
  </main>
</div>
</body>
</html>
