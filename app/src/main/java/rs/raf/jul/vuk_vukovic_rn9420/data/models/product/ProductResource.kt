package rs.raf.jul.vuk_vukovic_rn9420.data.models.product

sealed class ProductResource<out T>{
    data class Success<out T>(val data: T) : ProductResource<T>()
    data class Loading<out T>(val message: String = "") : ProductResource<T>()
    data class Error<out T>(val error: Throwable = Throwable(), val data: T? = null): ProductResource<T>()
}
