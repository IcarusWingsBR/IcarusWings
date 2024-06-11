package icaruswings

import grails.plugin.springsecurity.annotation.Secured
import icaruswings.adapters.UserAdapter

@Secured('ROLE_ADMIN')
class UserController {

    def userService

    def index() { }

    def addUser() {
        UserAdapter userAdapter = new UserAdapter(params)
        userService.addUser(userAdapter)

        flash.type = "success"
        flash.message = "Novo usuário criado com sucesso."

        redirect(action: "index", controller: "customer")
    }

    @Secured(['IS_AUTHENTICATED_FULLY'])
    def list() {
        Long customerId = Long.valueOf(params.customerId)
        return [userList: userService.list(customerId)]
    }
}
