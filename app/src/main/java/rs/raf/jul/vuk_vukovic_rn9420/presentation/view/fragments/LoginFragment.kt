package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jul.vuk_vukovic_rn9420.R
import rs.raf.jul.vuk_vukovic_rn9420.databinding.FragmentLoginBinding
import rs.raf.jul.vuk_vukovic_rn9420.presentation.contract.UserContract
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.UserState
import rs.raf.jul.vuk_vukovic_rn9420.presentation.viewmodel.UserViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserContract.ViewModel by sharedViewModel<UserViewModel>()

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
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (username.isNotBlank() && password.isNotBlank()){
                userViewModel.login(username, password)
            }
            else{
                Toast.makeText(context, "Login input can't be empty!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initObservers(){
        userViewModel.userState.observe(viewLifecycleOwner) {
            renderState(it)
        }
    }

    private fun renderState(state: UserState){
        when(state){
            is UserState.LoggedIn -> continueToApp()
            is UserState.LoggingIn -> Toast.makeText(context, "Attempting to login...", Toast.LENGTH_SHORT).show()
            is UserState.Error -> Toast.makeText(context, "Login error!", Toast.LENGTH_LONG).show()
            else -> return
        }
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