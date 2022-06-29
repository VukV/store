package rs.raf.jul.vuk_vukovic_rn9420.presentation.states

sealed class PriceState{
    data class Success(val totalPrice: Double): PriceState()
    data class Error(val message: String): PriceState()
    object Idle: PriceState()
}
