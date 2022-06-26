package rs.raf.jul.vuk_vukovic_rn9420.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase(){
    abstract fun getProductDao(): ProductDao
}