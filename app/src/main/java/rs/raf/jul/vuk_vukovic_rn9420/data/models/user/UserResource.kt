package rs.raf.jul.vuk_vukovic_rn9420.data.models.user

sealed class UserResource<out T>{
    data class LoggingIn<out T>(val message: String) : UserResource<T>()
    data class Success<out T>(val data: T) : UserResource<T>()
    data class Error<out T>(val error: Throwable = Throwable(), val data: T? = null): UserResource<T>()
}
