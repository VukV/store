package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.jul.vuk_vukovic_rn9420.R
import rs.raf.jul.vuk_vukovic_rn9420.data.*
import rs.raf.jul.vuk_vukovic_rn9420.databinding.FragmentProfileBinding
import rs.raf.jul.vuk_vukovic_rn9420.presentation.contract.UserContract
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.UserState
import rs.raf.jul.vuk_vukovic_rn9420.presentation.viewmodel.UserViewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val sharedPreferences: SharedPreferences by inject()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val userViewModel: UserContract.ViewModel by sharedViewModel<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initInfo()
        initListeners()
        initObservers()
    }

    private fun initInfo(){
        val pictureUrl = sharedPreferences.getString(pictureData, null)
        if (pictureUrl != null){
            Picasso.get().load(pictureUrl).into(binding.userPicture)
        }

        val firstName = sharedPreferences.getString(firstNameData, "")
        val lastName = sharedPreferences.getString(lastNameData, "")
        val fullName = "$firstName $lastName"

        val email = sharedPreferences.getString(emailData, "")
        val username = sharedPreferences.getString(usernameData, "")
        val gender = sharedPreferences.getString(genderData, "")

        binding.userFnameLname.text = fullName
        binding.userEmail.text = email
        binding.userName.text = username
        binding.userGender.text = gender
    }

    private fun initListeners(){
        binding.logoutButton.setOnClickListener {
            userViewModel.logout()
        }
    }

    private fun initObservers(){
        userViewModel.userState.observe(viewLifecycleOwner){
            renderState(it)
        }
    }

    private fun renderState(userState: UserState){
        when(userState){
            is UserState.LoggedOut -> returnToLogin()
            else -> return
        }
    }

    private fun returnToLogin(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragmentContainerMain, LoginFragment())
        transaction?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}