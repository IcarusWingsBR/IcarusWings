<atlas-sidebar
        slot="sidebar"
        home-path="${createLink(controller: "customer", action: "dashboard")}"
>
    <atlas-dropdown-button
            theme="primary"
            size="lg"
            block=""
            icon="plus"
            slot="header"
            tooltip=""
            tooltip-placement=""
            tooltip-trigger="hover focus"
            type="filled"
            pill=""
            hide-arrow
    >
        <atlas-dropdown-item value="payment"
                             tooltip-placement="bottom"
                             tooltip-trigger="hover focus"
                             href="/payment/index">
            Criar nova cobrança
        </atlas-dropdown-item>

        <atlas-dropdown-item value="payment"
                             tooltip-placement="bottom"
                             tooltip-trigger="hover focus"
                             href="/payer/index">
            Cadastrar novo pagador
        </atlas-dropdown-item>

    </atlas-dropdown-button>

    <atlas-sidebar-menu slot="body">

        <atlas-sidebar-menu-item
                value="dashboard"
                icon="dashboard"
                text="Resumo"
                href="${createLink(controller: "customer", action: "dashboard")}"
            ${ controllerName == "customer" && actionName == "dashboard" ? "active" : "" }>

        </atlas-sidebar-menu-item>

        <atlas-sidebar-menu-item
                icon="users"
                value="clients-group"
                text="Pagadores"
            ${ controllerName == "payer" ? "active" : "" }
        >
            <atlas-sidebar-menu-item
                    icon="users"
                    value="clients-group"
                    text="Cadastrar Pagador"
                    href="${createLink(controller: "payer", action: "index")}"
                ${ controllerName == "payer" && actionName == "index" ? "active" : "" }
            ></atlas-sidebar-menu-item>

            <atlas-sidebar-menu-item
                    icon="users"
                    value="clients-group"
                    text="Pagadores"
                    href="${createLink(controller: "payer", action: "list")}"
                ${ controllerName == "payer" && actionName == "list" ? "active" : "" }
            ></atlas-sidebar-menu-item>
        </atlas-sidebar-menu-item>

        <atlas-sidebar-menu-item
                icon="users"
                value="clients-group"
                text="Cobranças"
            ${ controllerName == "payment" ? "active" : "" }
        >
            <atlas-sidebar-menu-item
                    icon="money"
                    value="clients-group"
                    text="Cadastrar Cobrança"
                    href="${createLink(controller: "payment", action: "index")}"
                ${ controllerName == "payment" && actionName == "index" ? "active" : "" }
            ></atlas-sidebar-menu-item>

            <atlas-sidebar-menu-item
                    icon="money"
                    value="clients-group"
                    text="Listar Cobranças"
                    href="${createLink(controller: "payment", action: "list")}"
                ${ controllerName == "payment" && actionName == "list" ? "active" : "" }
            ></atlas-sidebar-menu-item>
        </atlas-sidebar-menu-item>
    </atlas-sidebar-menu>
</atlas-sidebar>