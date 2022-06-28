package rs.raf.jul.vuk_vukovic_rn9420.data.models.cart

import androidx.room.Entity

@Entity(tableName = "cart", primaryKeys = ["id", "username"])
data class CartProductEntity(
    val id: Int,
    val username:String,
    var amount: Int,
    val title: String,
    val price: Double
)
