package rs.raf.jul.vuk_vukovic_rn9420.data.repositories.product

import io.reactivex.Observable
import rs.raf.jul.vuk_vukovic_rn9420.data.datasources.local.ProductDao
import rs.raf.jul.vuk_vukovic_rn9420.data.datasources.remote.ProductService
import rs.raf.jul.vuk_vukovic_rn9420.data.models.category.Category
import rs.raf.jul.vuk_vukovic_rn9420.data.models.category.CategoryResource
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.Product
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.ProductEntity
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.ProductResource
import timber.log.Timber

class ProductRepositoryImpl(
    private val localDataSource: ProductDao,
    private val remoteDataSource: ProductService
) : ProductRepository{

    override fun fetchAll(): Observable<ProductResource<Unit>> {
        return remoteDataSource
            .getAll()
            .doOnNext {
                val products = it.products.map {
                    ProductEntity(
                        id = it.id,
                        title = it.title,
                        description = it.description,
                        price = it.price,
                        rating = it.rating,
                        category = it.category,
                        thumbnail = it.thumbnail
                    )
                }
                localDataSource.deleteAndInsertAll(products)
            }
            .map {
                ProductResource.Success(Unit)
            }
    }

    override fun getAll(): Observable<List<Product>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Product(
                        id = it.id,
                        title = it.title,
                        price = it.price,
                        rating = it.rating,
                        description = it.description,
                        category = it.category,
                        thumbnail = it.thumbnail
                    )
                }
            }
    }

    override fun fetchAllBySearch(searchTag: String): Observable<ProductResource<Unit>> {
        TODO("Not yet implemented")
    }

    override fun fetchAllCategories(): Observable<CategoryResource<Unit>> {
        TODO("Not yet implemented")
    }

    override fun getAllCategories(): Observable<List<Category>> {
        TODO("Not yet implemented")
    }
}