package icaruswings

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        group "/customer", {
            "/"(controller: "customer", action: [GET: "index"])

            "/"(controller: "customer", action: [POST: "save"])

            "/$id/show"(controller: "customer", action: [GET: "show"])

            "/update"(controller: "customer", action: [POST: "update"])

            "/list"(controller: "customer", action: [GET: "list"])
        }

        group "/payer", {
            "/"(controller: "payer", action: [GET: "index"])

            "/"(controller: "payer", action: [POST: "save"])

            "/$id/show"(controller: "payer", action: [GET: "show"])

            "/update"(controller: "payer", action: [POST: "update"])

            "/$id/delete"(controller: "payer", action: [POST: "deleted", DELETE: "delete"])

            "/$id/restore"(controller: "payer", action: [POST: "restore"])

            "/list"(controller: "payer", action: [GET: "list"])
        }

        group "/payment", {
            "/"(controller: "payment", action: [GET: "index"])

            "/"(controller: "payment", action: [POST: "save"])

            "/$id/show"(controller: "payment", action: [GET: "show"])

            "/update"(controller: "payment", action: [POST: "update"])

            "/$id/delete"(controller: "payment", action: [POST: "deleted", DELETE: "delete"])

            "/$id/restore"(controller: "payment", action: [POST: "restore"])

            "/list"(controller: "payment", action: [GET: "list", POST: "list"])

            "/$id/confirmPaymentReceived"(controller: "payment", action: [POST: "confirmPaymentReceived"])
        }

        group "/receipt", {
            "/$id/show"(controller: "receipt", action: [GET: "show"])
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
