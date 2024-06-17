package icaruswings

import grails.plugin.springsecurity.annotation.Secured
import icaruswings.adapters.UserAdapter

@Secured(['IS_AUTHENTICATED_FULLY'])
class UserController extends BaseController {

    def userService

    def index() {}

    def show() {
        Long id = Long.valueOf(params.id)
        User user = User.get(id)

        if (!user) render "Pagador não encontrado"

        return [user: user]
    }

    def addUser() {
        UserAdapter userAdapter = new UserAdapter(params)
        User user = userService.addUser((getAuthenticatedUser() as User).customer, userAdapter)

        flash.type = "success"
        flash.message = "Novo usuário criado com sucesso."

        redirect(action: "show", id: user.id)
    }

    def update() {
        UserAdapter userAdapter = new UserAdapter(params)
        userService.update(userAdapter)

        flash.type = "success"
        flash.message = "Alterações realizadas com sucesso."

        redirect(action: "show", id: params.id)
    }

    def delete() {
        Long id = Long.valueOf(params.id)

        userService.delete(getCurrentUser(), id)

        flash.type = "success"
        flash.message = "Usuário deletado com sucesso"

        redirect(action: "list")
    }

    def restore() {
        Long id = Long.valueOf(params.id)

        flash.type = "success"
        flash.message = "Usuário deletado com sucesso"

        userService.restore(getCurrentCustomerId(), id)

        redirect(action: "show", id: params.id)
    }

    def list() {
        String filter = params.userList

        return [userList: userService.list(getCurrentCustomerId(), filter)]
    }
}