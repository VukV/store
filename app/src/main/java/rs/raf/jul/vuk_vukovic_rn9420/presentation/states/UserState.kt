package rs.raf.jul.vuk_vukovic_rn9420.presentation.states

sealed class UserState{
    object LoggedOut: UserState()
    object LoggingIn: UserState()
    data class Error(val message: String): UserState()
    object LoggedIn: UserState()
}
