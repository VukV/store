package rs.raf.jul.vuk_vukovic_rn9420.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.ProductResponse
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.SingleProductResponse

interface ProductService {

    @GET("products")
    fun getAll(): Observable<ProductResponse>

    @GET("products/search")
    fun getAllBySearchTag(@Query("q") searchTag: String): Observable<ProductResponse>

    @GET("products/category/{c}")
    fun getAllByCategory(@Path("c") category: String): Observable<ProductResponse>

    @GET("products/categories")
    fun getAllCategories(): Observable<List<String>>

    @GET("products/{id}")
    fun getProductById(@Path("id") productId: Int): Observable<SingleProductResponse>
}