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

            "/save"(controller: "customer", action: [POST: "save"])

            "/show/$id"(controller: "customer", action: [GET: "show"])

            "/update"(controller: "customer", action: [POST: "update"])

            "/list"(controller: "customer", action: [GET: "list"])
        }

        group "/payer", {
            "/"(controller: "payer", action: [GET: "index"])

            "/save"(controller: "payer", action: [POST: "save"])

            "/show/$id"(controller: "payer", action: [GET: "show"])

            "/update"(controller: "payer", action: [POST: "update"])

            "/delete/$id"(controller: "payer", action: [POST: "deleted", DELETE: "delete"])

            "/restore/$id"(controller: "payer", action: [POST: "restore"])

            "/list"(controller: "payer", action: [GET: "list"])

            "/deletedList"(controller: "payer", action: [GET: "deletedList"])
        }

        group "/payment", {
            "/"(controller: "payment", action: [GET: "index"])

            "/save"(controller: "payment", action: [POST: "save"])

            "/show/$id"(controller: "payment", action: [GET: "show"])

            "/update"(controller: "payment", action: [POST: "update"])

            "/delete/$id"(controller: "payment", action: [POST: "deleted", DELETE: "delete"])

            "/restore/$id"(controller: "payment", action: [POST: "restore"])

            "/list"(controller: "payment", action: [GET: "list"])

            "/deletedList"(controller: "payment", action: [GET: "deletedList"])

            "/confirmPaymentReceived/$id"(controller: "payment", action: [POST: "confirmPaymentReceived"])
        }

        group "/receipt", {
            "/show/$id"(controller: "receipt", action: [GET: "show"])
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
