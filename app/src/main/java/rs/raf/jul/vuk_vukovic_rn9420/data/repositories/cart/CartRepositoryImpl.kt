package rs.raf.jul.vuk_vukovic_rn9420.data.repositories.cart

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jul.vuk_vukovic_rn9420.data.datasources.local.CartDao
import rs.raf.jul.vuk_vukovic_rn9420.data.models.cart.CartProduct

class CartRepositoryImpl(private val dataSource: CartDao) : CartRepository {

    override fun getAllFromCart(): Observable<List<CartProduct>> {
        TODO("Not yet implemented")
    }

    override fun removeAllFromCart(): Completable {
        TODO("Not yet implemented")
    }

    override fun addToCart(id: Int, amount: Int, title: String, price: Double): Completable {
        TODO("Not yet implemented")
    }

    override fun removeFromCart(productId: Int): Completable {
        TODO("Not yet implemented")
    }
}