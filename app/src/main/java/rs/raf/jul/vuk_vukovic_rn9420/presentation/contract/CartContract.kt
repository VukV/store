package rs.raf.jul.vuk_vukovic_rn9420.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.AddToCartState
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.CartState
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.PriceState

interface CartContract {

    interface ViewModel{

        val cartState: LiveData<CartState>
        val addToCartState: LiveData<AddToCartState>
        val priceState: LiveData<PriceState>

        fun getAllFromCart()
        fun removeAllFromCart()

        fun addToCart(id: Int, amount: Int = 1, title: String, price: Double)
        fun removeFromCart(productId: Int)

        fun getTotalPrice()

        fun idle()
    }
}