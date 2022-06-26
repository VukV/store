package rs.raf.jul.vuk_vukovic_rn9420.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.jul.vuk_vukovic_rn9420.modules.coreModule
import rs.raf.jul.vuk_vukovic_rn9420.modules.productModule
import rs.raf.jul.vuk_vukovic_rn9420.modules.userModule
import timber.log.Timber

class Store : Application() {

    override fun onCreate() {
        super.onCreate()

        setupTimber()
        setupKoin()
    }

    private fun setupTimber(){
        Timber.plant(Timber.DebugTree())
    }

    private fun setupKoin(){
        val modules = listOf(coreModule, userModule, productModule)

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@Store)
            androidFileProperties()
            fragmentFactory()
            modules(modules)
        }
    }
}