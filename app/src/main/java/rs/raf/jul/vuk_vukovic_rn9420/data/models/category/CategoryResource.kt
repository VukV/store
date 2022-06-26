package rs.raf.jul.vuk_vukovic_rn9420.data.models.category


sealed class CategoryResource<out T> {
    data class Success<out T>(val data: T) : CategoryResource<T>()
    data class Loading<out T>(val message: String) : CategoryResource<T>()
    data class Error<out T>(val error: Throwable = Throwable(), val data: T? = null): CategoryResource<T>()
}