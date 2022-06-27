package rs.raf.jul.vuk_vukovic_rn9420.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import rs.raf.jul.vuk_vukovic_rn9420.data.repositories.cart.CartRepository
import rs.raf.jul.vuk_vukovic_rn9420.presentation.contract.CartContract
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.AddToCartState
import rs.raf.jul.vuk_vukovic_rn9420.presentation.states.CartState

class CartViewModel(
    private val cartRepository: CartRepository
) : ViewModel(), CartContract.ViewModel{

    private val subscriptions = CompositeDisposable()
    override val cartState: MutableLiveData<CartState> = MutableLiveData()
    override val addToCartState: MutableLiveData<AddToCartState> = MutableLiveData()

    override fun getAllFromCart() {
        TODO("Not yet implemented")
    }

    override fun removeAllFromCart() {
        TODO("Not yet implemented")
    }

    override fun addToCart(id: Int, amount: Int, title: String, price: Double) {
        TODO("Not yet implemented")
    }

    override fun removeFromCart(productId: Int) {
        TODO("Not yet implemented")
    }

    override fun idle() {
        addToCartState.value = AddToCartState.Idle
    }
}