package rs.raf.jul.vuk_vukovic_rn9420.data.repositories.user

import io.reactivex.Observable
import rs.raf.jul.vuk_vukovic_rn9420.data.models.user.UserResource

interface UserRepository {

    fun login(username: String, password: String): Observable<UserResource<Unit>>
    fun logout(): Observable<Boolean>
}