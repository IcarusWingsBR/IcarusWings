package icaruswings

import icaruswings.utils.user.UserUtils

class CurrentUserTagLib {
    static namespace = "currentUserTagLib"

    Long getCurrentUserId = {
        println UserUtils.getCurrentUserId()

        out <<  UserUtils.getCurrentUserId()
    }
}