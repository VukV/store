package rs.raf.jul.vuk_vukovic_rn9420.presentation.states

import rs.raf.jul.vuk_vukovic_rn9420.data.models.product.SingleProduct

sealed class SingleProductState {
    object NoData: SingleProductState()
    data class Success(val product: SingleProduct): SingleProductState()
    data class Error(val message: String): SingleProductState()
}