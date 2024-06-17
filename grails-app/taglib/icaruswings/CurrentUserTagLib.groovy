package icaruswings

import icaruswings.utils.user.UserUtils

class CurrentUserTagLib {
    static namespace = "currentUserTagLib"

    Long getCurrentUserId = {
        out <<  UserUtils.getCurrentUserId()
    }
}