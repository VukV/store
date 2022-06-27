package rs.raf.jul.vuk_vukovic_rn9420.presentation.states

import rs.raf.jul.vuk_vukovic_rn9420.data.models.cart.CartProduct

sealed class CartState{
    data class Success(val cart: List<CartProduct>): CartState()
    data class Error(val message: String): CartState()
}
