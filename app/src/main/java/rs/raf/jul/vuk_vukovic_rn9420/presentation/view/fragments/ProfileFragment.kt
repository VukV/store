package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import rs.raf.jul.vuk_vukovic_rn9420.R
import rs.raf.jul.vuk_vukovic_rn9420.data.*
import rs.raf.jul.vuk_vukovic_rn9420.databinding.FragmentProfileBinding
import timber.log.Timber

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val sharedPreferences: SharedPreferences by inject()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

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
        initObservers()
    }

    private fun initInfo(){
        val pictureUrl = sharedPreferences.getString(pictureData, "")
        Picasso.get().load(pictureUrl).into(binding.userPicture)

        val firstName = sharedPreferences.getString(firstNameData, "")
        val lastName = sharedPreferences.getString(lastNameData, "")
        val email = sharedPreferences.getString(emailData, "")
        val username = sharedPreferences.getString(usernameData, "")
        val gender = sharedPreferences.getString(genderData, "")

        binding.userFnameLname.text = "$firstName $lastName"
        binding.userEmail.text = email
        binding.userName.text = username
        binding.userGender.text = gender
    }

    private fun initObservers(){
        binding.logoutButton.setOnClickListener {
            //TODO
        }
    }
}