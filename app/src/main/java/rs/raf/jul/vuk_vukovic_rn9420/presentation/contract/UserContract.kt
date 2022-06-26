package rs.raf.jul.vuk_vukovic_rn9420.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.UserState

interface UserContract {

    interface ViewModel{

        val userState: LiveData<UserState>

        fun login(username: String, password: String)
        fun logout()
    }
}