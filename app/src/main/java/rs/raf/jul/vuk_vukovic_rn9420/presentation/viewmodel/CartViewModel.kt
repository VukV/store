package rs.raf.jul.vuk_vukovic_rn9420.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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
        val subscription = cartRepository
            .getAllFromCart()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    cartState.value = CartState.Success(it)
                },
                {
                    cartState.value = CartState.Error("Data error")
                }
            )
        subscriptions.add(subscription)
    }

    override fun removeAllFromCart() {
        val subscription = cartRepository
            .removeAllFromCart()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    cartState.value = CartState.Emptied("Transaction done")
                },
                {
                    cartState.value = CartState.Error("Data error")
                }
            )
        subscriptions.add(subscription)
    }

    override fun addToCart(id: Int, amount: Int, title: String, price: Double) {
        val subscription = cartRepository
            .addToCart(id = id, title = title, price = price)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addToCartState.value = AddToCartState.Added("Item added to cart")
                },
                {
                    addToCartState.value = AddToCartState.Error("Data error")
                }
            )
        subscriptions.add(subscription)
    }

    override fun removeFromCart(productId: Int) {
        val subscription = cartRepository
            .removeFromCart(productId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //cartState.value = CartState.Emptied("Transaction done")
                },
                {
                    cartState.value = CartState.Error("Data error")
                }
            )
        subscriptions.add(subscription)
    }

    override fun idle() {
        addToCartState.value = AddToCartState.Idle
        cartState.value = CartState.Idle
    }
}