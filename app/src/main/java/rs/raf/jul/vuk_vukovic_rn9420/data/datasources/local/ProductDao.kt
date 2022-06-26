package rs.raf.jul.vuk_vukovic_rn9420.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.ProductEntity

@Dao
abstract class ProductDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<ProductEntity>): Completable

    @Query("SELECT * FROM products")
    abstract fun getAll(): Observable<List<ProductEntity>>

    @Query("DELETE FROM products")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<ProductEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }
}