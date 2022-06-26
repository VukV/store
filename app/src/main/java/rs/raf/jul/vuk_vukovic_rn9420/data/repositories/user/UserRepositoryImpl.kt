package rs.raf.jul.vuk_vukovic_rn9420.data.repositories.user

import android.content.SharedPreferences
import io.reactivex.Observable
import org.koin.android.ext.android.inject
import org.koin.experimental.property.inject
import rs.raf.jul.vuk_vukovic_rn9420.data.datasources.remote.UserService
import rs.raf.jul.vuk_vukovic_rn9420.data.models.user.LoginRequest
import rs.raf.jul.vuk_vukovic_rn9420.data.models.user.UserResource
import timber.log.Timber

class UserRepositoryImpl(
    private val dataSource: UserService,
    private val sharedPreferences: SharedPreferences
    ): UserRepository {


    override fun login(username: String, password: String): Observable<UserResource<Unit>> {
        val loginRequest = LoginRequest(username, password)

       return dataSource
           .login(loginRequest)
           .doOnNext {
               Timber.e("OVDE IDE SHARED PREF")
                //todo shared pref
           }
           .map {
               UserResource.Success(Unit)
           }
    }
}