package rs.raf.jul.vuk_vukovic_rn9420.data.models.product

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val rating: Double,
    val thumbnail: String
)
