package rs.raf.jul.vuk_vukovic_rn9420.data.models.product

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductResponse(
    val products: List<SingleProductResponse>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
