package rs.raf.jul.vuk_vukovic_rn9420.data.repositories.cart

import android.content.SharedPreferences
import android.database.sqlite.SQLiteConstraintException
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jul.vuk_vukovic_rn9420.data.datasources.local.CartDao
import rs.raf.jul.vuk_vukovic_rn9420.data.models.cart.CartProduct
import rs.raf.jul.vuk_vukovic_rn9420.data.models.cart.CartProductEntity
import rs.raf.jul.vuk_vukovic_rn9420.data.usernameData

class CartRepositoryImpl(
    private val dataSource: CartDao,
    private val sharedPreferences: SharedPreferences
) : CartRepository {

    override fun getAllFromCart(): Observable<List<CartProduct>> {
        val username = sharedPreferences.getString(usernameData, "")

        return dataSource
            .getAllByUser(username!!)
            .map {
                it.map {
                    CartProduct(
                        id = it.id,
                        username = it.username,
                        amount = it.amount,
                        title = it.title,
                        price = it.price
                    )
                }
            }
    }

    override fun removeAllFromCart(): Completable {
        val username = sharedPreferences.getString(usernameData, "")
        return dataSource.removeAllByUser(username!!)
    }

    override fun addToCart(id: Int, amount: Int, title: String, price: Double): Completable {
        val username = sharedPreferences.getString(usernameData, "")
        val cartProductEntity = CartProductEntity(
            id = id,
            username = username!!,
            amount = amount,
            title = title,
            price = price
        )

        //TODO FIX!
        return try {
            dataSource.addCartProduct(cartProductEntity)
        } catch (e: SQLiteConstraintException){
            cartProductEntity.amount = cartProductEntity.amount + 1
            dataSource.updateCartProduct(cartProductEntity)
        }

        //dataSource.addToCart(cartProductEntity)
    }

    override fun removeFromCart(productId: Int): Completable {
        val username = sharedPreferences.getString(usernameData, "")
        return dataSource.removeFromCartByUser(username!!, productId)
    }
}