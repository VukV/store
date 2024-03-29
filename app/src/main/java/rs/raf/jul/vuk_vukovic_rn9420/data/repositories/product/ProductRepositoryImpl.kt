package rs.raf.jul.vuk_vukovic_rn9420.data.repositories.product

import io.reactivex.Observable
import rs.raf.jul.vuk_vukovic_rn9420.data.datasources.local.ProductDao
import rs.raf.jul.vuk_vukovic_rn9420.data.datasources.remote.ProductService
import rs.raf.jul.vuk_vukovic_rn9420.data.models.category.CategoryEntity
import rs.raf.jul.vuk_vukovic_rn9420.data.models.category.CategoryResource
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.Product
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.ProductEntity
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.ProductResource
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.SingleProduct

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
        return remoteDataSource
            .getAllBySearchTag(searchTag)
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

    override fun fetchAllByCategory(category: String): Observable<ProductResource<Unit>> {
        return remoteDataSource
            .getAllByCategory(category)
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

    override fun fetchAllCategories(): Observable<CategoryResource<Unit>> {
        return remoteDataSource
            .getAllCategories()
            .doOnNext {
                val categories = it.map {
                    CategoryEntity(it)
                }
                localDataSource.deleteAndInsertAllCategories(categories)
            }
            .map {
                CategoryResource.Success(Unit)
            }
    }

    override fun getAllCategories(): Observable<List<String>> {
        return localDataSource
            .getAllCategories()
            .map {
                it.map {
                    it.name
                }
            }
    }

    override fun fetchSingleProduct(productId: Int): Observable<SingleProduct> {
        return remoteDataSource
            .getProductById(productId)
            .map {
                SingleProduct(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    category = it.category,
                    price = it.price,
                    rating = it.rating,
                    discountPercentage = it.discountPercentage,
                    brand = it.brand,
                    images = it.images
                )
            }
    }
}