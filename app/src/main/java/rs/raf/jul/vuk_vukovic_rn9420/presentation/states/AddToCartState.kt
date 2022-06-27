package rs.raf.jul.vuk_vukovic_rn9420.presentation.states

sealed class AddToCartState{
    object Idle: AddToCartState()
    data class Added(val message: String): AddToCartState()
    data class Error(val message: String): AddToCartState()
}
