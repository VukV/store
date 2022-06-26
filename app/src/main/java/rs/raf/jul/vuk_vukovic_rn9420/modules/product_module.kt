package rs.raf.jul.vuk_vukovic_rn9420.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jul.vuk_vukovic_rn9420.data.datasources.local.Database
import rs.raf.jul.vuk_vukovic_rn9420.data.datasources.remote.ProductService
import rs.raf.jul.vuk_vukovic_rn9420.data.repositories.product.ProductRepository
import rs.raf.jul.vuk_vukovic_rn9420.data.repositories.product.ProductRepositoryImpl
import rs.raf.jul.vuk_vukovic_rn9420.presentation.viewmodel.ProductViewModel

val productModule = module {

    viewModel { ProductViewModel(productRepository = get()) }
    single<ProductRepository> { ProductRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }
    single { get<Database>().getProductDao() }
    single<ProductService> { create(retrofit = get()) }
}