package rs.raf.jul.vuk_vukovic_rn9420.data.repositories.cart

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jul.vuk_vukovic_rn9420.data.models.cart.CartProduct

interface CartRepository {

    fun getAllFromCart(): Observable<List<CartProduct>>
    fun removeAllFromCart(): Completable

    fun addToCart(id: Int, amount: Int = 1, title: String, price: Double): Completable
    fun removeFromCart(productId: Int): Completable

    fun getTotalPrice(): Observable<Double>
}