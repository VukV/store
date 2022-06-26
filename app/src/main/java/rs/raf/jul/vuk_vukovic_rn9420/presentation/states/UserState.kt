package rs.raf.jul.vuk_vukovic_rn9420.presentation.states

import rs.raf.jul.vuk_vukovic_rn9420.data.models.user.User

sealed class UserState{
    object LoggedOut: UserState()
    object LoggingIn: UserState()
    data class Error(val message: String): UserState()
    object LoggedIn: UserState()
}
