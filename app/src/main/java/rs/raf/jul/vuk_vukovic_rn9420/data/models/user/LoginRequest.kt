package rs.raf.jul.vuk_vukovic_rn9420.data.models.user

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
    val username: String,
    val password: String
)
