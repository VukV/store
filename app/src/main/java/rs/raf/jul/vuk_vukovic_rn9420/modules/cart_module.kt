package rs.raf.jul.vuk_vukovic_rn9420.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jul.vuk_vukovic_rn9420.data.datasources.local.Database
import rs.raf.jul.vuk_vukovic_rn9420.data.repositories.cart.CartRepository
import rs.raf.jul.vuk_vukovic_rn9420.data.repositories.cart.CartRepositoryImpl
import rs.raf.jul.vuk_vukovic_rn9420.presentation.viewmodel.CartViewModel

val cartModule = module {

    viewModel { CartViewModel(cartRepository = get()) }
    single<CartRepository> { CartRepositoryImpl(dataSource = get(), sharedPreferences = get()) }
    single { get<Database>().getCartDao() }
}