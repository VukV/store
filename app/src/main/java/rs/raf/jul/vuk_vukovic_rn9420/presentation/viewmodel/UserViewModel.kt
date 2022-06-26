package rs.raf.jul.vuk_vukovic_rn9420.presentation.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.experimental.property.inject
import rs.raf.jul.vuk_vukovic_rn9420.data.models.user.UserResource
import rs.raf.jul.vuk_vukovic_rn9420.data.repositories.user.UserRepository
import rs.raf.jul.vuk_vukovic_rn9420.presentation.contract.UserContract
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.UserState

class UserViewModel(
    private val userRepository: UserRepository,
    ) :ViewModel(), UserContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val userState: MutableLiveData<UserState> = MutableLiveData()

    override fun login(username: String, password: String) {
        val subscription = userRepository
            .login(username, password)
            .startWith(UserResource.LoggingIn("Attempting Login"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it){
                        is UserResource.LoggingIn -> userState.value = UserState.LoggingIn
                        is UserResource.Error -> userState.value = UserState.Error("Login Error")
                        is UserResource.Success -> userState.value = UserState.LoggedIn
                    }
                },
                {
                    userState.value = UserState.Error("Login Error")
                }
            )
        subscriptions.add(subscription)
    }

    override fun logout() {
        val subscription = userRepository
            .logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it){
                        userState.value = UserState.LoggedOut
                    }
                },
                {
                    userState.value = UserState.Error("Logout error!")
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}