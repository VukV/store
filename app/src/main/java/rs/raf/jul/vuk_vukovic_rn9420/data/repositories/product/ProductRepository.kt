package rs.raf.jul.vuk_vukovic_rn9420.data.repositories.product

import io.reactivex.Observable
import rs.raf.jul.vuk_vukovic_rn9420.data.models.category.Category
import rs.raf.jul.vuk_vukovic_rn9420.data.models.category.CategoryResource
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.Product
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.ProductResource

interface ProductRepository {

    fun fetchAll(): Observable<ProductResource<Unit>>
    fun getAll(): Observable<List<Product>>

    fun fetchAllBySearch(searchTag: String): Observable<ProductResource<Unit>>

    fun fetchAllCategories(): Observable<CategoryResource<Unit>>
    fun getAllCategories(): Observable<List<Category>>
}