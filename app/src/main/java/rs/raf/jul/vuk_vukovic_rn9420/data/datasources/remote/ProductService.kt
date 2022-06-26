package rs.raf.jul.vuk_vukovic_rn9420.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.ProductResponse

interface ProductService {

    @GET("products")
    fun getAll(): Observable<ProductResponse>
}