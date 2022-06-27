package rs.raf.jul.vuk_vukovic_rn9420.data.repositories.product

import io.reactivex.Observable
import rs.raf.jul.vuk_vukovic_rn9420.data.models.category.CategoryResource
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.Product
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.ProductResource
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.SingleProduct
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.SingleProductResponse

interface ProductRepository {

    fun fetchAll(): Observable<ProductResource<Unit>>
    fun getAll(): Observable<List<Product>>

    fun fetchAllBySearch(searchTag: String): Observable<ProductResource<Unit>>
    fun fetchAllByCategory(category: String): Observable<ProductResource<Unit>>

    fun fetchAllCategories(): Observable<CategoryResource<Unit>>
    fun getAllCategories(): Observable<List<String>>

    fun fetchSingleProduct(productId: Int): Observable<SingleProduct>
}