package rs.raf.jul.vuk_vukovic_rn9420.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import rs.raf.jul.vuk_vukovic_rn9420.data.models.user.LoginRequest
import rs.raf.jul.vuk_vukovic_rn9420.data.models.user.UserResponse

interface UserService {

    @POST("auth/login")
    @Headers("Content-Type: application/json")
    fun login(@Body loginRequest: LoginRequest): Observable<UserResponse>
}