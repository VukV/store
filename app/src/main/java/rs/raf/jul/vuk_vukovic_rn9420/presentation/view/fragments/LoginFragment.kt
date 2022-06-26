package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jul.vuk_vukovic_rn9420.R
import rs.raf.jul.vuk_vukovic_rn9420.data.alreadyLoggedIn
import rs.raf.jul.vuk_vukovic_rn9420.databinding.FragmentLoginBinding
import rs.raf.jul.vuk_vukovic_rn9420.presentation.contract.UserContract
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.UserState
import rs.raf.jul.vuk_vukovic_rn9420.presentation.viewmodel.UserViewModel
import timber.log.Timber

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserContract.ViewModel by sharedViewModel<UserViewModel>()

    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()
    }

    private fun initListeners(){
        binding.loginButton.setOnClickListener {
            userViewModel.login("kminchelle", "0lelplR")
        }
    }

    private fun initObservers(){
        userViewModel.userState.observe(viewLifecycleOwner) {
            renderState(it)
        }
    }

    private fun renderState(state: UserState){
        when(state){
            is UserState.LoggedIn -> Timber.e("LOGGED")
            is UserState.LoggingIn -> Timber.e("INIT")
            else -> Timber.e("NE VALJA")
        }
    }

    private fun loginToSharedPref(){
        sharedPreferences.edit().putBoolean(alreadyLoggedIn, true).apply()
    }

    private fun continueToApp(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragmentContainerMain, MainFragment())
        transaction?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}