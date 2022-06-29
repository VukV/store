package rs.raf.jul.vuk_vukovic_rn9420.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jul.vuk_vukovic_rn9420.data.datasources.remote.UserService
import rs.raf.jul.vuk_vukovic_rn9420.data.repositories.user.UserRepository
import rs.raf.jul.vuk_vukovic_rn9420.data.repositories.user.UserRepositoryImpl
import rs.raf.jul.vuk_vukovic_rn9420.presentation.viewmodel.UserViewModel

val userModule = module {
    viewModel { UserViewModel(userRepository = get()) }
    single<UserRepository> { UserRepositoryImpl(dataSource = get(), sharedPreferences = get()) }
    single<UserService> { create(retrofit = get()) }
}