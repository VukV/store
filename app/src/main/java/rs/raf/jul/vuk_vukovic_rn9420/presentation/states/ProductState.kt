package rs.raf.jul.vuk_vukovic_rn9420.presentation.states

import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.Product

sealed class ProductState{
    object Loading: ProductState()
    object DataFetched: ProductState()
    data class Success(val products: List<Product>): ProductState()
    data class Error(val message: String): ProductState()
}
