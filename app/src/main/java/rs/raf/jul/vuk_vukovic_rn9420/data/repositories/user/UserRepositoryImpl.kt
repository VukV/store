package rs.raf.jul.vuk_vukovic_rn9420.data.repositories.user

import android.content.SharedPreferences
import io.reactivex.Observable
import org.koin.android.ext.android.inject
import org.koin.experimental.property.inject
import rs.raf.jul.vuk_vukovic_rn9420.data.*
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
               sharedPreferences.edit().putString(usernameData, it.username).apply()
               sharedPreferences.edit().putString(emailData, it.email).apply()
               sharedPreferences.edit().putString(firstNameData, it.firstName).apply()
               sharedPreferences.edit().putString(lastNameData, it.lastName).apply()
               sharedPreferences.edit().putString(genderData, it.gender).apply()
               sharedPreferences.edit().putString(pictureData, it.image).apply()

               sharedPreferences.edit().putBoolean(alreadyLoggedIn, true).apply()
           }
           .map {
               UserResource.Success(Unit)
           }
    }

    override fun logout(): Observable<Boolean> {
        sharedPreferences.edit().putString(usernameData, "").apply()
        sharedPreferences.edit().putString(emailData, "").apply()
        sharedPreferences.edit().putString(firstNameData, "").apply()
        sharedPreferences.edit().putString(lastNameData, "").apply()
        sharedPreferences.edit().putString(genderData, "").apply()
        sharedPreferences.edit().putString(pictureData, null).apply()

        sharedPreferences.edit().putBoolean(alreadyLoggedIn, false).apply()

        return Observable.just(true)
    }
}