package rs.raf.jul.vuk_vukovic_rn9420.data.models.product

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SingleProductResponse(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val rating: Double,
    val thumbnail: String,
    val discountPercentage: Double,
    val brand: String,
    val images: List<String>
)
