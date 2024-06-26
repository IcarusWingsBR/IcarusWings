<!doctype html>
<html lang="pt-BR">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
    <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <link
            rel="stylesheet"
            href="https://atlas.asaas.com/v15.18.0/atlas.min.css"
            crossorigin="anonymous">
    <script
            defer
            src="https://atlas.asaas.com/v15.18.0/atlas.min.js"
            crossorigin="anonymous"
    ></script>
    <g:layoutHead/>
    <style>
    body, html {
        height: 100%;
    }
    </style>
</head>
<body>
<atlas-screen>
    <atlas-page class="js-atlas-page">
        <atlas-page-content slot="content" class="js-atlas-content">
            <g:layoutBody />
        </atlas-page-content>
    </atlas-page>
</atlas-screen>
</body>
</html>