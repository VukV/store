package rs.raf.jul.vuk_vukovic_rn9420.presentation.view.activities

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.koin.android.ext.android.inject
import org.koin.experimental.property.inject
import rs.raf.jul.vuk_vukovic_rn9420.data.alreadyLoggedIn
import rs.raf.jul.vuk_vukovic_rn9420.databinding.ActivityMainBinding
import rs.raf.jul.vuk_vukovic_rn9420.presentation.view.fragments.LoginFragment
import rs.raf.jul.vuk_vukovic_rn9420.presentation.view.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    private val sharedPreferences: SharedPreferences by inject()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition{
            start()
            false
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun start(){
        if(checkLogin()){
            startMainFragment()
        }
        else{
            startLoginFragment()
        }
    }

    private fun checkLogin(): Boolean {
        return sharedPreferences.getBoolean(alreadyLoggedIn, false)
    }

    private fun startMainFragment(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.fragmentContainerMain.id, MainFragment())
        transaction.commit()
    }

    private fun startLoginFragment(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.fragmentContainerMain.id, LoginFragment())
        transaction.commit()
    }
}