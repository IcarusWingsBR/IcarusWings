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
    <asset:javascript src="/customerNotification/customerNotificationController.js"/>
</head>
<body>
<atlas-screen>
    <g:render template="/utils/sidebar" />

    <atlas-navbar slot="navbar">
        <div slot="actions">
            <atlas-icon-button
                    icon="bell"
                    data-atlas-dropdown="notifications-dropdown"
                    hoverable
                    tooltip-placement="bottom"
                    tooltip-trigger="hover focus"
            ></atlas-icon-button>
            <atlas-dropdown
                header="Notificações"
                id="notifications-dropdown"
                placement="bottom-start"
                trigger="click"
                width="300"
                auto-close
                auto-close-trigger="outside"
                loading
                class="js-notification-button"
                >
                <div class="js-div-content"></div>
            </atlas-dropdown>
        </div>
        <div slot="actions">
            <atlas-avatar
                    data-atlas-dropdown="profile-dropdown"
                    hoverable show-carret
            ></atlas-avatar>
            <atlas-dropdown
                    id="profile-dropdown"
                    placement="bottom-start"
                    trigger="click"
                    width="300"
                    auto-close
                    auto-close-trigger="outside">
                <atlas-dropdown-item
                        icon="cog"
                        theme="secondary"
                        href="${createLink(controller: 'customer', action: 'show')}"
                >
                    Minha conta
                </atlas-dropdown-item>
                <atlas-dropdown-item
                        icon="power"
                        theme="danger"
                        href="http://localhost:8080/logout"
                >Sair</atlas-dropdown-item>
            </atlas-dropdown>
        </div>
    </atlas-navbar>

    <atlas-page class="js-atlas-page">
        <atlas-page-header
                slot="header"
                page-name="${pageProperty(name: "body.page-title")}"
        >
            <atlas-breadcrumb slot="breadcrumb">
                <atlas-breadcrumb-item
                        text="${pageProperty(name: "body.page-title")}"
                        icon="home"></atlas-breadcrumb-item
                >
            </atlas-breadcrumb>
        </atlas-page-header>
        <atlas-page-content slot="content" class="js-atlas-content">
            <g:layoutBody />
        </atlas-page-content>
    </atlas-page>
</atlas-screen>
</body>
</html>